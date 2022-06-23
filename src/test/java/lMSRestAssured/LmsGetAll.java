package lMSRestAssured;


import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class LmsGetAll {
	
	@Test
	void Test_03() 
    {
		//RequestSpecification httpRequest = RestAssured.given();
	//	Response response = given().auth().basic("admin", "password").when().get("/programs");
		
		baseURI = "https://lms-admin-rest-service.herokuapp.com";
		given().auth()
			.basic("admin", "password")
		.when()
			.get("/programs")
		.then().statusCode(200)
			.log().all()
		;
		//.body("x[1].programId", equalTo(5594));
		//int statusCode = response.statusCode();
		//System.out.println("Status Code is :" + statusCode);
		
	}
	
	@Test
	void Test_04()
	//check for unauthorized get method
	{
		
		Response response = get("https://lms-admin-rest-service.herokuapp.com");
		baseURI = "https://lms-admin-rest-service.herokuapp.com";
		given()
			.get("/programs")
		.then().statusCode(401)
		;
		
		int statusCode = response.statusCode();
		System.out.println("Show Status Code 401:" + statusCode);
		Assert.assertEquals(statusCode, 401);
		//response.then().assertThat().body(matchesJsonSchemaInClasspath("C:\\Users\\shobh\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\resources\\Properties\\LmsSchema.json"));
		
	}

	
	

}
