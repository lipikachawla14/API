package businessLogic;

import commonUtilities.CommonUtills;
import dataSource.LoadProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BusinessLogic {
	public static RequestSpecification httpReq;
	public static Response resp;
	protected static Object Revision;
	
	public static int getRevision() {
		int rev = Integer.parseInt((String) Revision);
		Revision =rev;
		return rev;
	}
	public void createRequest(String req) {
		RestAssured.baseURI= LoadProperties.getvariable("BaseUrl", "config");
		httpReq = RestAssured.given();
		if(req.equalsIgnoreCase("getalllists")) {
			httpReq= CommonUtills.addHeader(httpReq);
			httpReq.log().all();
		}
		else if (req.equalsIgnoreCase("getSpecificList")) {
			httpReq.pathParam("id", LoadProperties.getvariable("GetListID", "config"));
			httpReq = CommonUtills.addHeader(httpReq);
		}
		else if (req.equalsIgnoreCase("createList"))
		{	
			httpReq = CommonUtills.addHeader(httpReq);
			httpReq.body(CommonUtills.createBody(req));
		}
		else if (req.equalsIgnoreCase("patchList"))
		{
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",LoadProperties.getvariable("GetListID","config"), httpReq);
			httpReq.body(CommonUtills.createBody(req));
			httpReq.log().all();
		}
		else if(req.equalsIgnoreCase("putList"))
		{
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",LoadProperties.getvariable("GetListID","config"), httpReq);
			httpReq.body(CommonUtills.createBody(req));
			httpReq.log().all();
			
		}
		else if(req.equalsIgnoreCase("deleteList"))
		{
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",LoadProperties.getvariable("GetListID","config") ,httpReq);
			CommonUtills.addPathVariable("revision",LoadProperties.getvariable("Revision", "config"), httpReq);
		}
		else if(req.equalsIgnoreCase("createTask"))
		{
			httpReq =CommonUtills.addHeader(httpReq);
			httpReq.body(CommonUtills.createBody(req));
			
		}
		
		else {
			System.out.println("Request not found ");
		}
			
	}
	
	public void createResponse(String res) {
		if(res.equalsIgnoreCase("getAllLists")) {
			resp = httpReq.when().get("/lists");
			resp.then().log().all().assertThat().statusCode(200);
		}
		else if(res.equalsIgnoreCase("getSpecificList")) {
			resp = httpReq.when().get("lists");
			resp.then().log().all().assertThat().statusCode(200);
		}
		else if(res.equalsIgnoreCase("createList")) {
			resp=httpReq.when().post("lists");
			
			resp.then().log().all().assertThat().statusCode(201);
			Revision = resp.jsonPath().get("revision");
		}
		else if(res.equalsIgnoreCase("patchList")) {
			resp = httpReq.when().patch("lists/{id}");
			resp.then().log().all().assertThat().statusCode(200);
		}
		else if(res.equalsIgnoreCase("putList")) {
			resp = httpReq.when().put("lists/{id}");
			resp.then().log().all().statusCode(200);
		}						
	    else if(res.equalsIgnoreCase("deleteList")) {
			resp = httpReq.when().delete("lists/{id}");	
			resp.then().log().all().statusCode(204);
	    }
		else if(res.equalsIgnoreCase("CreateTask")){
			resp = httpReq.when().post("task");
			resp.then().log().all().statusCode(201);
		}
		else {
			System.out.println("Response not found ");
		}

	}
}
