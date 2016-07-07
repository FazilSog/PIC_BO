package com.sogeti.jenkins;

import java.io.FileNotFoundException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class ConsoleSonar {

	//Adresse de jenkins
	private static final String ADDRESS_SONAR = "http://localhost:9000";

	//public void Create (String nomProjet) throws FileNotFoundException {
	public static void main(String[] args) throws FileNotFoundException {	
		
		String nomProjet = "PIC";
		String strURL = ADDRESS_SONAR + "/api/issues/search?componentRoots=" + nomProjet;
		//File input = new File("D:/config.xml");
		GetMethod get = new GetMethod(strURL);
		
		//post.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));


		get.setRequestHeader("Content-type", "text/xml; charset=ISO-8859-1");
		HttpClient httpclient = new HttpClient();

		try {

            int result = httpclient.executeMethod(get);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(get.getResponseBodyAsString());
		} catch (Exception e) {
            // Release current connection to the connection pool 
            // once you are done
            get.releaseConnection();
        }
		


	}
	
	
	
	

}
