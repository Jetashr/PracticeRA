package lMSRestAssured;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
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
public class LmsDeletaData {
	@SuppressWarnings({ "unused", "unchecked" })
	@Test(dataProvider="deletedataprovider")
    public void DeleteProgramDetails(String progId)
	{

    RestAssured.baseURI="https://lms-admin-rest-service.herokuapp.com";

	//Request object
	RequestSpecification httpRequest = RestAssured.given();

	//Request payload sending along with post request
	JSONObject requestParams = new JSONObject();
    requestParams.put("programId", progId);
	
	Response response = RestAssured.given().auth().basic("admin", "password").
	header("Content-Type", "application/json").
	when().delete("/programs/"+progId).then().assertThat().log().all().
	extract().response();

	//Capture response body to perform validations
	String responseBody = response.getBody().asString();
	System.out.println("Response body is: "+ responseBody);

	//Assert.assertEquals(responseBody.contains(progName), true);
	//Assert.assertEquals(responseBody.contains(progDes), true);
	// assertThat(responseBody,matchesJsonSchemaInClasspath("LmsSchema.json"));
}

@DataProvider(name = "deletedataprovider")
	String[][] deletedata() throws IOException {
	String path="C://Users//shobh//eclipse-workspace//RestAssuredAutomation//TestData//LmsData.xlsx";
	int rownum = XLUtils.getRowCount(path, "Delete");
	int colnum = XLUtils.getCellCount(path, "Delete", 1);
	String progdata[][] = new String[rownum][colnum];
	for (int i = 1; i <= rownum; i++)
	{
	for (int j = 0; j < colnum; j++)
	{
	progdata[i - 1][j] = XLUtils.getCellData(path, "Delete", i, j);
	}
	}
	return progdata;
	}
	
	
}
	
