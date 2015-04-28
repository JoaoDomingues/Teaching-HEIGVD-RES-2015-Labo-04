package ch.heigvd.res.labs.http.impl;

import ch.heigvd.res.labs.http.interfaces.IHttpResponse;
import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Olivier Liechti
 */
public class HttpClientTest {
   @Test
   public void itShouldBePossibleToSendAGETRequest(){
      HttpClient client = new HttpClient();
      HttpRequest request = new HttpRequest();
      
      request.setMethod("GET");
      request.setProtocolVersion("HTTP/1.1");
      request.setURI("http://www.heig-vd.ch/presentation");
      
      IHttpResponse response = client.sendRequest(request);
      
      assertEquals(200, response.getStatusCode());
   }
   
   @Test
   public void itShouldBePossibleToHandleA302StatusCode(){ 
      HttpClient client = new HttpClient();
      HttpRequest request = new HttpRequest();
      
      request.setMethod("GET");
      request.setProtocolVersion("HTTP/1.1");
      request.setURI("http://www.google.com");
      
      IHttpResponse response = client.sendRequest(request);
      
      assertEquals(302, response.getStatusCode());
   }
   
   @Test
   public void itShouldReturn258LengthData(){
      HttpClient client = new HttpClient();
      HttpRequest request = new HttpRequest();
      
      request.setMethod("GET");
      request.setProtocolVersion("HTTP/1.1");
      request.setURI("http://www.google.com");
      
      IHttpResponse response = client.sendRequest(request);
      
      assertEquals(258, response.getContentLength());
   }
   
   @Test
   public void itShouldReturnAHtmlUTF8BodyData() throws UnsupportedEncodingException{
      HttpClient client = new HttpClient();
      HttpRequest request = new HttpRequest();
      
      request.setMethod("GET");
      request.setProtocolVersion("HTTP/1.1");
      request.setURI("http://www.google.com");
      
      IHttpResponse response = client.sendRequest(request);
      Assert.assertTrue(response.getHeader("content-type").getValues()[1].replaceAll("charset=", "").equalsIgnoreCase("utf-8"));
   }
}