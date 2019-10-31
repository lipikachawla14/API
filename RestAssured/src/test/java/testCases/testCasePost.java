package testCases;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testCasePost extends BaseClass{
	RequestSpecification httpRequest;
	Response response;
	
	@Test
	public void request() {
		createRequest("createList");
		createResponse("createTask");
		logger.info("response");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
	}
	
	@Test
	public void response() {
		createRequest("createList");
		createResponse("createTask");
		logger.info("check_response");
		String responseBody = response.getBody().asString();
		logger.info("Response =" +responseBody);
		
	
	}
	
	
}
