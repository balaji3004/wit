package wit;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WitClientTry
{
	public static void main(String[] args) {
		 HttpClient httpclient = null;
	try {
          httpclient = HttpClientBuilder.create().build();  // the http-client, that will send the request
          //HttpGet httpGet = new HttpGet("https://api.wit.ai/converse?v=20161110&session_id=123abc&q=status");   // the http GET request
          Random r = new Random();
          int rnum= r.nextInt(1900);
          System.out.println(rnum);
          String randomString = rnum+"abc";
          System.out.println(randomString);
          HttpPost httpPost = new HttpPost("https://api.wit.ai/converse?v=20161113&session_id="+randomString);	 
          StringEntity input = new StringEntity("{\"expression\":\"Get me the status of my ticket\"}");
          input.setContentType("application/json");
          httpPost.setEntity(input);	            
          
          httpPost.addHeader("Authorization", "Bearer PPPUGN45XFSXCGSXVQHV2DE76M4TYN2W"); // add the authorization header to the request
          httpPost.addHeader("Content-Type", "application/json");
          httpPost.addHeader("Accept", "application/json");
          
          HttpResponse response = httpclient.execute(httpPost); // the client executes the request and gets a response
          int responseCode = response.getStatusLine().getStatusCode();  // check the response code
          switch (responseCode) {
              case 200: { 
                  // everything is fine, handle the response
                 String stringResponse = EntityUtils.toString(response.getEntity());  // now you have the response as String, which you can convert to a JSONObject or do other stuff
                  System.out.println("Json response" + stringResponse);
                  //Parse our JSON response               
                  JSONParser j = new JSONParser();
                  JSONObject o = null;
				try {
					o = (JSONObject)j.parse(stringResponse);
				} catch (org.json.simple.parser.ParseException e) {
					e.printStackTrace();
				}
                  String action = (String)o.get("action");
    
                  System.out.println(action);
               // If the response does not enclose an entity, there is no need
                  // to bother about connection release
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
      }	
	catch (IOException | ParseException ex) {
          // handle exception
      }
	finally {
	      // When HttpClient instance is no longer needed,
	      // shut down the connection manager to ensure
	      // immediate deallocation of all system resources
		httpclient.getConnectionManager().shutdown();
	    }
	
	}
	
	public static String buildtheConversation(String conversation)
	{
		conversation = conversation + "";
		return conversation;
	}
	
  }
