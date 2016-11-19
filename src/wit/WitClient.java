package wit;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class WitClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  try {
	            HttpClient httpclient = HttpClientBuilder.create().build();  // the http-client, that will send the request
	            //HttpGet httpGet = new HttpGet("https://api.wit.ai/converse?v=20161110&session_id=123abc&q=status");   // the http GET request
	            
	            HttpPost httpPost = new HttpPost("https://api.wit.ai/converse?v=20161110&session_id=148abc");	 
	            StringEntity input = new StringEntity("{\"expression\":\"status\"}");
	            input.setContentType("application/json");
	            httpPost.setEntity(input);	            
	            
	            httpPost.addHeader("Authorization", "Bearer FZFKMR3LEXGAJSJXGASE5PGFND4PQNBQ"); // add the authorization header to the request
	            httpPost.addHeader("Content-Type", "application/json");
	            httpPost.addHeader("Accept", "application/json");
	            
	            HttpResponse response = httpclient.execute(httpPost); // the client executes the request and gets a response
	            int responseCode = response.getStatusLine().getStatusCode();  // check the response code
	            switch (responseCode) {
	                case 200: { 
	                    // everything is fine, handle the response
	                    String stringResponse = EntityUtils.toString(response.getEntity());  // now you have the response as String, which you can convert to a JSONObject or do other stuff
	                    System.out.println("json response" + stringResponse);
	                    
	                    break;
	                }
	                case 500: {
	                    // server problems ?
	                    break;
	                }
	                case 403: {
	                    // you have no authorization to access that resource
	                    break;
	                }
	            }
	        } catch (IOException | ParseException ex) {
	            // handle exception
	        }
	    }
	
	public static String buildMessage(String message)
	{
		
		return message;
	}

	}


