import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Autherezation {
	
	
	@Test
	 void AutherizationTest()
	 {
	  
	  //Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/authentication/CheckForAuthentication";
	  
	  //Basic authentication
		PreemptiveBasicAuthScheme authschme = new PreemptiveBasicAuthScheme();
		authschme.setUserName("ToolsQA");
		authschme.setPassword("TestPassword");
		
		RestAssured.authentication=authschme;
		

		
		//Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
	  Response response=httpRequest.request(Method.GET,"/");
	  
	    
	    // status line validation
	    
	    String statusLine = response.getStatusLine();
	    System.out.println("Status LINE is: " + statusLine);
	    Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	    
	    // print response in console windows 
	    
	    String responseBody = response.getBody().asString();
	    System.out.println("Response body is: " + responseBody);
	    

	    // Status code validation
	    
	    int statusCode = response.getStatusCode();
	    System.out.println("Status cod is: " + statusCode);
	    Assert.assertEquals(statusCode, 200);
	    
	}


}
