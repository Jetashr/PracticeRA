	package lMSRestAssured;
import utils.XLUtils;
import java.io.File;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@SuppressWarnings("unused")
public class PutData {

    @SuppressWarnings("unchecked")
	@Test(dataProvider="putdataprovider")
    void putNewProgramDetails(String progId,String progName,String progDes,String eonline)
	{

	//Specify base URI
	RestAssured.baseURI="https://lms-admin-rest-service.herokuapp.com";

	//Request object
	//RequestSpecification httpRequest = RestAssured.given();

	//Request payload sending along with post request
	JSONObject requestParams = new JSONObject();

	requestParams.put("programId", progId);
	requestParams.put("programName", progName);
	requestParams.put("programDescription", progDes);
	requestParams.put("online", eonline);


	Response response = RestAssured.given().auth().basic("admin", "password").
	header("Content-Type", "application/json").body(requestParams.toJSONString()).
	when().put("/programs/"+progId).then().assertThat().log().all().
	extract().response();

	//Asserting the status code is success
	int statusCode = response.getStatusCode();
	//Assert.assertEquals(statusCode,200);
	System.out.println("The response code is "+statusCode);

	//Capture response body to perform validations
	String responseBody = response.getBody().asString();
	System.out.println("Response body is: "+ responseBody);
    
	//schema validation
	 assertThat("JsonSchema",responseBody,JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\shobh\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\resources\\Properties\\JObsSchema.json")));
    System.out.println("JsonSchemaValidation is validated successfully");

}
    
    
    private void assertThat(String string, String responseBody, JsonSchemaValidator matchesJsonSchema) {}


    @DataProvider(name = "putdataprovider")
	String[][] putdata() throws IOException {
	String path="C://Users//shobh//eclipse-workspace//RestAssuredAutomation//TestData//LmsData.xlsx";
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

	
	