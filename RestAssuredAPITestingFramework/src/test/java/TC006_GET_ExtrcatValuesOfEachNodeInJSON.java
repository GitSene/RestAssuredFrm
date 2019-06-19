import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtrcatValuesOfEachNodeInJSON {
	
	
	@Test
	 void googleMapTest()
	 {
	  // connect the databse
		// get the dc weather form a sele 
		// put in to a vairable
		//int weather =18
	  //Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
	  Response response=httpRequest.request(Method.GET,"/Washington");
	  
	  
	 JsonPath jsonpath = response.jsonPath();
	 System.out.println(jsonpath.getString("City"));
	 System.out.println(jsonpath.getString("Temperature"));
	 System.out.println(jsonpath.getString("Humidity"));
	 System.out.println(jsonpath.getString("WeatherDescription"));
	 System.out.println(jsonpath.getString("WindSpeed"));
	 System.out.println(jsonpath.getString("WindDirectionDegree"));
	 
	  Assert.assertEquals(jsonpath.get("Temperature"), " Degree celsius"); // This is not consistance 
	}


}
