package lMSRestAssured;
import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.XLUtils;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class Postdatadriven {

	@SuppressWarnings({ "unchecked", "unused" })
	@Test(dataProvider="programdetails")
	public void postdata(String progDesc,String progName,String Online) 
	{
	RestAssured.baseURI="https://lms-admin-rest-service.herokuapp.com";
	RequestSpecification httpsRequest=RestAssured.given();
   //creating data to be sent along with post request
	JSONObject requestParams=new JSONObject();
	//requestParams.put("programID",progId);
	requestParams.put("programName",progName);
	requestParams.put("programDescription",progDesc);
	requestParams.put("online",Online);

	//adding header to state that request body ia a json
	//httpsRequest.header("content-type","application/json",null);

	Response response = RestAssured.given()
	.auth().basic("admin", "password")
	.header("Content-Type", "application/json")
	.body(requestParams.toJSONString())
	.when().post("/programs").
	then().assertThat().statusCode(200)
	.log().all().
	extract().response();
	
	//.body(JsonSchemaValidator.matchesJsonSchema("LmsSchema.json"));
	//add json to the body of the request
	httpsRequest.body(requestParams.toJSONString());
	httpsRequest.auth().basic("admin","password");
	//Response response=httpsRequest.request(Method.POST,"/programs");

    //Capturing response body to perform validations
	String responseBody=response.getBody().asString();
	System.out.println("ResponseBody is" + responseBody);

	//Assert.assertEquals(responseBody.contains(progId),true);
	Assert.assertEquals(responseBody.contains(progName),true);
	Assert.assertEquals(responseBody.contains(progDesc),true);
	// Assert.assertEquals(ProgrameName,progName);
	//Assert.assertEquals(ProgramDescription,progDesc);

	//validation of the statuscode
	int statusCode=response.getStatusCode();
	Assert.assertEquals(statusCode,200);
	System.out.println("StatusCode is :" +statusCode);
    
     //schema validation	
	assertThat("JsonSchema",responseBody,JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\shobh\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\resources\\Properties\\LmsSchema.json")));
	System.out.println("Jsonschemavalidation is validated successfully");
	
	}	
	
	private void assertThat(String string, String responseBody, JsonSchemaValidator matchesJsonSchema) {}
	
	@DataProvider(name = "programdetails")
	String[][] postdata() throws IOException {
	String path="C://Users//shobh//eclipse-workspace//RestAssuredAutomation//TestData//LmsData.xlsx";
    int rownum = XLUtils.getRowCount(path, "Post");
	int colnum = XLUtils.getCellCount(path, "Post", 1);
	String progdata[][] = new String[rownum][colnum];
	for (int i = 1; i <= rownum; i++)
	{
	for (int j = 0; j < colnum; j++)
	{
	progdata[i-1][j] = XLUtils.getCellData(path,"Post",i,j);
	}
	}
	return progdata;
	}
}
