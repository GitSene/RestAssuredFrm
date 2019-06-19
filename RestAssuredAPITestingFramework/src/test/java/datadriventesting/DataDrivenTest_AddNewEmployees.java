package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {

	
	@Test(dataProvider="empdataprovider")
	
	void addNewEmployees(String  ename, String eage, String esalary)
{
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		// to see all employees list just get this link in postman
		// "http://dummy.restapiexample.com/api/v1/employees"
		
		//hear we created data which we can send  along with the post request
		
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", ename);
		requestParams.put("salary", esalary);
		requestParams.put("age", eage);
		
		
		//add a header stating the Request body is JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the JSON to the body of the request
		
		httpRequest.body(requestParams.toJSONString());
		
		//Post REquest
		
		Response response = httpRequest.request(Method.POST, "/create");
		
		
		// capture repose body to perform validations
		
	String responseBody	= response.getBody().asString();
	System.out.println("Response body is:" +  responseBody);
	
	Assert.assertEquals(responseBody.contains(ename), true);
	Assert.assertEquals(responseBody.contains(esalary), true);
	Assert.assertEquals(responseBody.contains(eage), true);
	
	 int statuscode = response.getStatusCode();
	 
	 Assert.assertEquals(statuscode, 200);
	 
	}

	
	
	
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException
	
	{
		
		String path=System.getProperty("user.dir")+"/src/test/java/datadriventesting/empdata.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1",1);
		
		String empdata[][] =new String [rownum][colcount];
		
		for (int i=1; i<=rownum; i++ ) {
			
			for (int j =0; j<colcount; j++ ) {
				empdata[i-1][j] = XLUtils.getCellData(path, "Sheet1",i,j);
			}
			
		}
		
		
		//String empdata[][]= {{"abcd173","50000","32"},{"xdvc345","80000","45"},{"hnid34","99000","39"}};
		
		return(empdata);
	}




}


