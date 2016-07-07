package com.sogeti.jenkins;

import java.io.FileNotFoundException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class ConsoleJenkins {
	
	//Adresse de jenkins
		private static final String ADDRESS_JENKINS = "http://localhost:8083";

		//public void Create (String nomProjet) throws FileNotFoundException {
		public static void main(String[] args) throws FileNotFoundException {	
			
			//http://localhost:8083/job/PIC_BO/lastBuild/logText/progressiveText?start=0
			
			String nomProjet = "PIC_BO";
			String strURL = ADDRESS_JENKINS + "/job/" + nomProjet + "/lastBuild/logText/progressiveText?start=0";
			GetMethod post = new GetMethod(strURL);
			//post.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));


			post.setRequestHeader("Content-type", "text/xml; charset=ISO-8859-1");
			HttpClient httpclient = new HttpClient();

			try {

	            int result = httpclient.executeMethod(post);
	            System.out.println("Response status code: " + result);
	            System.out.println("Response body: ");
	            System.out.println(post.getResponseBodyAsString());
			} catch (Exception e) {
	            // Release current connection to the connection pool 
	            // once you are done
	            post.releaseConnection();
	        }
			


		}
	

}
