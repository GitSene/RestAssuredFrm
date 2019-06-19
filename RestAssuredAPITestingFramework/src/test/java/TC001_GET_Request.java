import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GET_Request {
	
	
	@Test
	void getweatherDetails()
	{
		
		
		//specify base URI
		
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request object
		RequestSpecification httprequest = RestAssured.given();
	    
	    
	    // Response Object
	    
	    Response response = httprequest.request(Method.GET, "/Washington");
	    
	    // print response in console window
	    
	    String responseBody = response.getBody().asString();
	    System.out.println("Response body is:  " + responseBody);
	    
	   
	    
	    // Status code validation
	    
	    int statusCode = response.getStatusCode();
	    System.out.println("Status cod is: " + statusCode);
	    Assert.assertEquals(statusCode, 200);
	    
	    // status line validation
	    
	    String statusLine = response.getStatusLine();
	    System.out.println("Status LINE is: " + statusLine);
	    Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	    
	    
	    
	    
	}
	

}
