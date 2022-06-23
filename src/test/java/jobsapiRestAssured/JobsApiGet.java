package jobsapiRestAssured;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JobsApiGet {
    @Test
    void jobsget() {
    	//specify base uri
    	RestAssured.baseURI="https://jobs123.herokuapp.com";
    	
    	//Request object
    	RequestSpecification httpRequest=RestAssured.given();
    	//Response object
    	Response response=httpRequest.request(Method.GET,"/Jobs");
    	
    	//Print response in console window
    	String responseBody=response.getBody().asString();
        System.out.println("Response Body is :" +responseBody);
        System.out.println("Content type is : " +response.getHeader("content-type"));
        Assert.assertEquals(responseBody!=null,true);
        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is:" +statusCode);
        Assert.assertEquals(statusCode,200);
      
        //JsonSchemaValidation
        String  getresponse=response.getBody().asString().replaceAll("NaN","\" 1 hr\"");
        System.out.println("Replaces response values for NaN :" +getresponse);
       
        //schema validation
        MatcherAssert.assertThat(getresponse,JsonSchemaValidator.matchesJsonSchemaInClasspath("Properties\\JObsSchema.json"));
        System.out.println("Jsonschemavalidation is validated successfully");
 }
	
}
	

