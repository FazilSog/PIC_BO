package com.sogeti.jenkins;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuildItem {
	

	private static final String ADDRESS_JENKINS = "http://localhost:8083";
	
	@Transactional
	public String launchBuild(String nomProjet) throws ClientProtocolException, IOException{
	//public static void main(String[] args) throws ClientProtocolException, IOException {	
		
	    nomProjet = "PIC_FO";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpGet getRequest = new HttpGet(ADDRESS_JENKINS + "/job/"+nomProjet+"/build");
		
		HttpResponse response = httpClient.execute(getRequest);
		
		Header[] location = response.getHeaders("Location");
		
		
		if(response.getStatusLine().getStatusCode() != 201){
			throw new RuntimeException("Erreur HTTP " + response.getStatusLine().getStatusCode());
		}
		
		return location[0].getValue();
	}

	

}
