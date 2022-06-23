package lMSRestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetData {
   @Test
	void test_01(){
		//response object
    	Response response=RestAssured.get("https://lms-admin-rest-service.herokuapp.com");
    	
    	//Request object
    	RequestSpecification httpsRequest=RestAssured.given();
    	httpsRequest.auth().basic("admin","password");
    	
		//printing responses in the window
    	System.out.println("Status time is :" + response.getTime());
		System.out.println("Status Body is : " +response.getBody().asString());
	    System.out.println("Content type is : " +response.getHeader("content-type"));
		
		//status code validation
		int statusCode=response.getStatusCode();
		System.out.println("Status code is : " + response.getStatusCode());
		Assert.assertEquals(statusCode,401);
		
		//status line verification
		String statusLine=response.getStatusLine();
		System.out.println("Status Line is:" + statusLine);
		Assert.assertEquals(statusLine.trim(),"HTTP/1.1 401");
}
	
//@Test
void Test_02()
{
	Response response=RestAssured.get("https://lms-admin-rest-service.herokuapp.com");
	RequestSpecification httpsRequest=RestAssured.given();
	httpsRequest.auth().basic("admin","password");
	
	System.out.println("Status time is :" + response.getTime());
	System.out.println("Status Body is : " +response.getBody().asString());
    System.out.println("Content type is : " +response.getHeader("content-type"));
	
  //status code validation
  	int statusCode=response.getStatusCode();
  	System.out.println("Status code is : " + statusCode );
  	//Assert.assertEquals(statusCode,200);
  		
}


}

