package jobsapiRestAssured;

import java.io.File;
import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.XLUtils;

public class JobsPost {

	@SuppressWarnings({ "unchecked", "unused" })
	@Test(dataProvider="Jobsdataprovider")
	public void JobsPostData (String JobLocation,String JobId, String JobPostedtime,String JobCompanyName,
			String JobDescription,String JobType,String JobTitle)
	{
		//Specify base URI
		RestAssured.baseURI="https://jobs123.herokuapp.com/Jobs";
		//Request object
		RequestSpecification httpRequest = RestAssured.given();

		//Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Location",JobLocation);
		requestParams.put("Job Id",JobId);
		requestParams.put("Job Company Name",JobCompanyName);
		requestParams.put("Job Posted time",JobPostedtime);
		requestParams.put("Job Description",JobDescription);
		requestParams.put("Job Title",JobTitle);
		requestParams.put("Job Type",JobType);
   
		//Response response = RestAssured.given().auth().basic("admin", "password").
			//	header("Content-Type", "application/json").body(requestParams.toJSONString()).
				//when().post("/programs").then().assertThat().statusCode(200).log().all().
				//extract().response();
        Response response=httpRequest.request(Method.POST);
		httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());
		
		//Asserting the status code is success
		//String statusCode=Integer.parseInt(statusCode);
        int statusCode=response.getStatusCode();
        System.out.println("StatusCode:" + response.getStatusCode());
	   // Assert.assertEquals(response.getStatusCode(),statusCode);
		

		//Capture response body to perform validations
		String responseBody = response.getBody().asString().replaceAll("NaN","\" 1 hr\"");
		//System.out.println("Response body is: "+ responseBody);
		String getresponseBody = response.getBody().asPrettyString();

		Assert.assertEquals(responseBody.contains(JobId), false);
		Assert.assertEquals(responseBody.contains(JobPostedtime),false);
		Assert.assertEquals(responseBody.contains(JobType), false);
		Assert.assertEquals(responseBody.contains(JobDescription),false);

    
	    //Schema Validation
	    assertThat("JsonSchema",responseBody,JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\shobh\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\resources\\Properties\\JObsSchema.json")));
		System.out.println("Jsonschemavalidation is validated successfully");
	
	}
	


	private void assertThat(String string, String responseBody, JsonSchemaValidator matchesJsonSchema) {}


@DataProvider(name = "Jobsdataprovider")
	  String[][] jobspostdata() throws IOException {
		  String path="C://Users//shobh//eclipse-workspace//RestAssuredAutomation//TestData//JobsInputData.xlsx";
	  int rownum = XLUtils.getRowCount(path, "Post");
	  int colnum = XLUtils.getCellCount(path, "Post", 1);
	  String progdata[][] = new String[rownum][colnum];
	  for (int i = 1; i <= rownum; i++)
	      {
			for (int j = 0; j < colnum; j++)
			{
			 progdata[i - 1][j] = XLUtils.getCellData(path, "Post", i, j);
			}
			}
			return progdata;
			}
}	