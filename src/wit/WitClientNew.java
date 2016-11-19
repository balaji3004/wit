package wit;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class WitClientNew
{
	public static void main(String[] args) {
		 HttpClient httpclient = null;
	try {
          httpclient = HttpClientBuilder.create().build();  // the http-client, that will send the request
          //HttpGet httpGet = new HttpGet("https://api.wit.ai/converse?v=20161110&session_id=123abc&q=status");   // the http GET request
          
          HttpPost httpPost = new HttpPost("https://api.wit.ai/converse?v=20161113&session_id=1689abc");	 
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
                /*  String stringResponse = EntityUtils.toString(response.getEntity());  // now you have the response as String, which you can convert to a JSONObject or do other stuff
                  System.out.println("Json response" + stringResponse);*/
                  
               // If the response does not enclose an entity, there is no need
                  // to bother about connection release
                  HttpEntity entity = response.getEntity();
                  byte[] buffer = new byte[1024];
                  if (entity != null) {
                    InputStream inputStream = entity.getContent();
                    try {
                      int bytesRead = 0;
                      BufferedInputStream bis = new BufferedInputStream(inputStream);
                      while ((bytesRead = bis.read(buffer)) != -1) {
                        String chunk = new String(buffer, 0, bytesRead);
                        System.out.println(chunk);
                      }
                    } catch (IOException ioException) {
                      // In case of an IOException the connection will be released
                      // back to the connection manager automatically
                      ioException.printStackTrace();
                    } catch (RuntimeException runtimeException) {
                      // In case of an unexpected exception you may want to abort
                      // the HTTP request in order to shut down the underlying
                      // connection immediately.
                    	httpPost.abort();
                      runtimeException.printStackTrace();
                    } finally {
                      // Closing the input stream will trigger connection release
                      try {
                        inputStream.close();
                      } catch (Exception ignore) {
                      }
                    }
                  }                
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
	catch (ClientProtocolException e) {
	      // thrown by httpClient.execute(httpGetRequest)
	      e.printStackTrace();
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
