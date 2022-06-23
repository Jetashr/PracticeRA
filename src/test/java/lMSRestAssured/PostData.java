package lMSRestAssured;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import utils.XLUtils;

public class PostData {
@SuppressWarnings({ "unchecked", "rawtypes" })
@Test
public void TC_01()
{
RestAssured.baseURI ="https://lms-admin-rest-service.herokuapp.com";

RequestSpecification request = RestAssured.given().auth().basic("admin", "password");

JSONObject requestParams = new JSONObject();

request.header("Content-Type", "application/json", null);
requestParams.put("programName", "testing");
requestParams.put("programDescription", "Tester123");
requestParams.put("online", true);


request.body(requestParams.toJSONString());

Response response = request.post("/programs");
ResponseBody body = response.getBody();

System.out.println(response.getStatusLine());
System.out.println(body.asString());

}

}














































/*@SuppressWarnings("unchecked")
@Test(dataProvider="programdetails")
public void postdata(String progDesc,String progName,String progId,String Online) 
{
RestAssured.baseURI="https://lms-admin-rest-service.herokuapp.com";
RequestSpecification httpsRequest=RestAssured.given();

//creating data to be sent along with post request
JSONObject requestParams=new JSONObject();
requestParams.put("programID",progId);
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


}	

/*  @DataProvider(name="programdetails")
public Object[][] postEmpData() throws IOException
{
Object[][] empdata = XLUtils.getCellDataExcel("C:/Users/shobh/eclipse-workspace/RestAssuredAutomation/TestData/Empdata.xlsx");
return(empdata);	
}
}
*/
/*@DataProvider(name = "programdetails")
public Object[][] postdata() throws IOException {
	
//DataFormatter formatter = new DataFormatter();	
String path = System.getProperty("user.dir") + "/src/test/java/LmsData.xlsx";
int rownum = XLUtils.getRowCount(path, "Post.json");
int colnum = XLUtils.getCellCount(path, "Post.json", 1);
String progdata[][] = new String[rownum][colnum];
for (int i = 1; i <= rownum; i++)
{
for (int j = 0; j < colnum; j++)
{
progdata[i - 1][j] = XLUtils.getCellData(path,"Post",i,j);
}
}
return progdata;
}
}
	
	
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@DataProvider(name="postData")
//	public Object[][] dataForPost(){
//
//	/*Object[][] data = new Object[3][4];
//
//	data[1][0]= "7629";
//
//	return data;*/
//
//	return new Object[][] {
//	{"Testing","Tesing","8456","True"},
//	{"Java","Develepor","8457","True"},
//	{"Devops","Developer","8756","True"}
//
//	
//	};
	
/*
	@SuppressWarnings({ "unchecked", "static-access" })
	@Test(dataProvider = "postData")
	public void testPost(String eprogramName,
	String eprogramDescription, String Online) {

	JSONObject request = new JSONObject();
	//request.put("eprogramId",eprogramId);
	request.put("eprogramName",eprogramName);
	request.put("eprogramDescription",eprogramDescription);
	request.put("Online",Online);

	@SuppressWarnings("unused")
	String baseURI = "https://lms-admin-rest-service.herokuapp.com";

	
	given().with()
	.basic("admin", "password").
	contentType(ContentType.JSON).
	accept(ContentType.JSON).
	header("Content-Type","application/json",null)
	.body(request.toJSONString()).
	when().
	post("/programs").
	then().
	statusCode(200).
	log().all();

		
	
	}

	private RestAssured given() {
		// TODO Auto-generated method stub
		return null;
	}
}
	
*/	
	






