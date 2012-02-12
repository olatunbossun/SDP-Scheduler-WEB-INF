package com.vas.util.servlets;

import java.io.IOException;
//import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
//import java.util.Properties;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;



import org.apache.log4j.Logger;

import com.vas.util.LoadAllProperties;

public class SendSmsToKannelService {
	
	private Logger logger;
	//private InputStream inputStream = null;
	private LoadAllProperties properties;
	
	
	public SendSmsToKannelService(){
		
		logger = Logger.getLogger(SendSmsToKannelService.class);
		
		/*inputStream = this.getClass().getResourceAsStream("KannelConfiguration.properties");
		properties = new Properties();
		
		try {
			logger.info(SendSmsToKannelService.class+
					"Loading the kannel connection properties file");
			
			properties.load(inputStream);
			
			logger.info(SendSmsToKannelService.class+
					"Loaded the Kannel properties file");
		} catch (IOException e) {
			
			logger.debug(SendSmsToKannelService.class+
					"There was an error while loading the kannel connection file");
			e.printStackTrace();
		}*/
		
	}
	
	public void setProperties(LoadAllProperties properties){
		this.properties = properties;
	}
	
	/**
	 * Utility method to send message to kannel from this servlet.
	 * @param message
	 */
	public void sendMessageToKannel(String message, String phoneNumber, String username,
			String password){
		
		String smsMessage = "";
		try{			
			smsMessage = URLEncoder.encode(message,"UTF-8");	
			
		}
		catch(UnsupportedEncodingException ex){
			logger.debug(SendSmsToKannelService.class+ ":sendMessageToKannel() has thrown an exception\n"
					+ ex.getMessage());			
		}
				
		String kannelUrl = "http://10.210.7.193:13009/cgi-bin/sendsms";
		
		String kannelParam = "?username="+username+"&password="+password+"&to="+phoneNumber+"&text="+
		smsMessage+"&from=0802";
		
		HttpClient client = new HttpClient();
		
		GetMethod method = new GetMethod(kannelUrl+kannelParam);
		
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		new DefaultHttpMethodRetryHandler(3, false));
		
		try {
		      // Execute the method.
		      int statusCode = client.executeMethod(method);

		      if (statusCode != HttpStatus.SC_OK) {
		        System.err.println("Method failed: " + method.getStatusLine());
		      }

		      // Read the response body.
		      byte[] responseBody = method.getResponseBody();

		      // Deal with the response.
		      // Use caution: ensure correct character encoding and is not binary data
		      System.out.println(new String(responseBody));

		    } catch (HttpException e) {
		      System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		    } catch (IOException e) {
		      System.err.println("Fatal transport error: " + e.getMessage());
		      e.printStackTrace();
		    } finally {
		      // Release the connection.
		      method.releaseConnection();
		    }  		
	
	}
	
	/**
	 * This is a method that sends out SMS to a subscriber using the supplied message and phonenumber
	 * @param message
	 * @param phoneNumber
	 */
	public void sendMessageToKannel(String message, String phoneNumber){
		
		String smsMessage = "";
		try{			
			smsMessage = URLEncoder.encode(message,"UTF-8");	
			
		}
		catch(UnsupportedEncodingException ex){
			logger.debug(SendSmsToKannelService.class+ ":sendMessageToKannel() has thrown an exception\n"
					+ ex.getMessage());			
		}
				
		
		String kannelUrl = "http://"+properties.getProperty("kannelurl")+":"+properties.getProperty("kannelport")+
		"/cgi-bin/sendsms";
		
		System.out.println("The value of the url is = "+kannelUrl);
		
		String kannelParam = "?username="+properties.getProperty("kannelusername")+
		"&password="+properties.getProperty("kannelpassword")+"&to="+phoneNumber+"&text="+
		smsMessage+"&from=0802";
		
		HttpClient client = new HttpClient();
		
		GetMethod method = new GetMethod(kannelUrl+kannelParam);
		
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		new DefaultHttpMethodRetryHandler(3, false));
		
		try {
		      // Execute the method.
		      int statusCode = client.executeMethod(method);

		      if (statusCode != HttpStatus.SC_OK) {
		        System.err.println("Method failed: " + method.getStatusLine());
		      }

		      // Read the response body.
		      byte[] responseBody = method.getResponseBody();

		      // Deal with the response.
		      // Use caution: ensure correct character encoding and is not binary data
		      System.out.println(new String(responseBody));

		    } catch (HttpException e) {
		      System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		    } catch (IOException e) {
		      System.err.println("Fatal transport error: " + e.getMessage());
		      e.printStackTrace();
		    } finally {
		      // Release the connection.
		      method.releaseConnection();
		    } 
	
	}
	
	
public void sendMessageToKannel(String message, Collection phoneNumbers){
		
		String smsMessage = "";
		try{			
			smsMessage = URLEncoder.encode(message,"UTF-8");	
			
		}
		catch(UnsupportedEncodingException ex){
			logger.debug(SendSmsToKannelService.class+ ":sendMessageToKannel() has thrown an exception\n"
					+ ex.getMessage());			
		}
				
		
		String kannelUrl = "http://"+properties.getProperty("kannelurl")+":"+properties.getProperty("kannelport")+
		"/cgi-bin/sendsms";
		
		System.out.println("The value of the url is = "+kannelUrl);
		
		/*String kannelParam = "?username="+properties.getProperty("kannelusername")+
		"&password="+properties.getProperty("kannelpassword")+"&to="+phoneNumber+"&text="+
		smsMessage+"&from=0802";*/
		
		HttpClient client = new HttpClient();
		
		//GetMethod method = new GetMethod(kannelUrl+kannelParam);
		GetMethod method = new GetMethod(kannelUrl);
		
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		new DefaultHttpMethodRetryHandler(3, false));
		
		try {
		      // Execute the method.
		      int statusCode = client.executeMethod(method);

		      if (statusCode != HttpStatus.SC_OK) {
		        System.err.println("Method failed: " + method.getStatusLine());
		      }

		      // Read the response body.
		      byte[] responseBody = method.getResponseBody();

		      // Deal with the response.
		      // Use caution: ensure correct character encoding and is not binary data
		      System.out.println(new String(responseBody));

		    } catch (HttpException e) {
		      System.err.println("Fatal protocol violation: " + e.getMessage());
		      e.printStackTrace();
		    } catch (IOException e) {
		      System.err.println("Fatal transport error: " + e.getMessage());
		      e.printStackTrace();
		    } finally {
		      // Release the connection.
		      method.releaseConnection();
		    } 
	
	}
	
}
