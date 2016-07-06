package com.sogeti.jenkins;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;




public class CreateJob {

	public void CreateItem(String nomProjet) throws FileNotFoundException {
		String strURL = "http://localhost:8083/createItem?name=${nomProjet}";
		File input = new File("D:/config.xml");
		PostMethod post = new PostMethod(strURL);
		post.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));


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