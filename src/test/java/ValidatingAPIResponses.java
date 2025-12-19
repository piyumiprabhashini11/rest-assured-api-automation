import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import files.Payload;
import files.ReusableMethods;
import groovyjarjarantlr4.v4.misc.Utils;

public class ValidatingAPIResponses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in the response
		//Validate if Add Place API is working as expected
		//given - All input details
		//when - Submit the API - resource,http method(POST/GET)
		//Then - Validate the response
		// url- https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		//URI - https://rahulshettyacademy.com/
		//Resource - maps/api/place/add/json
		//queryParam - key=qaclick123
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Add Place
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/jason")
		.body(Payload.addPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println(response);
//		JsonPath js = new JsonPath(response); //for pasing json / converting the string (response) to json
		JsonPath js = ReusableMethods.rawToJason(response); //Create a Json object by passing the string response
		String placeId=js.getString("place_id"); //get the value relavant to place id attribute from the json object
		System.out.println("This is the Placeid" + placeId );  
		
		//Update Place 
		String newAddress = "Summer Walk,Africa";
		given().log().all().queryParam("place_id", "cbeef2741cce7486303e61bbc3df2945").header("Content-Type", "application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\r\n"
				+ "\"address\":\""+newAddress+",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg",equalTo("Update address operation failed, looks like the data doesn't exists"));	
	  
		//Get Place (1st Method to Validate the Updated Address)
	   given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
	   .body("address", equalTo(newAddress));
	
	   //Get Place (2nd Method to Validate the Updated Address)
	   String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).when().get("maps/api/place/get/json").then().assertThat().statusCode(200)
	   .extract().response().asString();
	   //JsonPath js2 = new JsonPath(getPlaceResponse);
	   JsonPath js2 = ReusableMethods.rawToJason(getPlaceResponse);
	   String actualAddress =js2.getString("address");
	   Assert.assertEquals(actualAddress, newAddress );   
	      
	}

}
