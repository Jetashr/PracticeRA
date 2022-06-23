package jobsapiRestAssured;

import java.io.File;
import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.XLUtils;

public class JobsPut {

	@SuppressWarnings({ "unchecked", "unused" })
	@Test(dataProvider="Jobsdataprovider")
	public void JobsPutData (String JobId,String JobTitle)
	{
		//Specify base URI
		RestAssured.baseURI="https://jobs123.herokuapp.com/Jobs";
		//Request object
		RequestSpecification httpRequest = RestAssured.given();

		//Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("Job Id",JobId);
		requestParams.put("Job Title",JobTitle);
		
        Response response=httpRequest.request(Method.POST);
		httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());
		
		
		//Asserting the status code is success
		//int statusCode=Integer.parseInt(statusCode);
        String responseBody=response.getBody().asString();
        int statusCode=response.getStatusCode();
        System.out.println("StatusCode:" + response.getStatusCode());
	   // Assert.assertEquals(response.getStatusCode(),statusCode);
		
        //Capture response body to perform validations
		String getresponseBody = response.getBody().asString().replaceAll("NaN","\" 1 hr\"");
		//System.out.println("Response body is: "+ getresponseBody);
		

		Assert.assertEquals(responseBody.contains(JobId), false);
		Assert.assertEquals(responseBody.contains(JobTitle),false);
		

	//MatcherAssert.assertThat(response,JsonSchemaValidator.matchesJsonSchemaInClasspath("JObsSchema.json"));
		 assertThat("JsonSchema",responseBody,JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\shobh\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\resources\\Properties\\JObsSchema.json")));
}


	private void assertThat(String string, String responseBody, JsonSchemaValidator matchesJsonSchema) {
		// TODO Auto-generated method stub
		
	}


	@DataProvider(name = "Jobsdataprovider")
	  String[][] jobsputdata() throws IOException {
		  String path="C://Users//shobh//eclipse-workspace//RestAssuredAutomation//TestData//JobsInputData.xlsx";
	  int rownum = XLUtils.getRowCount(path, "Put");
	  int colnum = XLUtils.getCellCount(path, "Put", 1);
	  String progdata[][] = new String[rownum][colnum];
	  for (int i = 1; i <= rownum; i++)
	      {
			for (int j = 0; j < colnum; j++)
			{
			 progdata[i - 1][j] = XLUtils.getCellData(path, "Put", i, j);
			}
			}
			return progdata;
			}
}

