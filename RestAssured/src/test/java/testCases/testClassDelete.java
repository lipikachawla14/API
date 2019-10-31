package testCases;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class testClassDelete extends BaseClass {
	@Test
	public void request() {
		createRequest("deleteLists");
		logger.info("get_all_info");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
		}
	
	@Test
	public void response() {
		createResponse("deleteLists");
		logger.info("check_response");
		String responseBody = response.getBody().asString();
		logger.info("Response =" +responseBody);
		
	}

}
