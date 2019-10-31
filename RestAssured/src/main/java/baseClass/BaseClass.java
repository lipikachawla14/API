package baseClass;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import businessLogic.BusinessLogic;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass extends BusinessLogic{
	String req = null;
	public boolean getLists() {
		req = "getalllists";
		
		try {
		req = "getSpecificList";
        createRequest(req);
		createResponse(req);
		return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static RequestSpecification httpRequest;
	public static Response response;
	
	public Logger logger;
	
	@BeforeClass
	public void start() {
		
		logger=Logger.getLogger("Wunderlist");
		PropertyConfigurator.configure("Log4j.properties");
	}
		
	

}
