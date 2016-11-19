package wit;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author home
 *
 */
public class OAuthConnect {

    public static void main(String[] args) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();  // the http-client, that will send the request
            HttpGet httpGet = new HttpGet("https://api.wit.ai/message?v=20161110&q=hello");   // the http GET request
            httpGet.addHeader("Authorization", "Bearer ADV4X6TJZOVY2NNENKZFQ4PGIN3UBEKM"); // add the authorization header to the request
            HttpResponse response = httpclient.execute(httpGet); // the client executes the request and gets a response
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
}