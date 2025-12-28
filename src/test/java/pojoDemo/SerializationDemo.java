package pojoDemo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojoSerialization.Location;
import pojoSerialization.SetGoogleMapDetails;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationDemo {
	
	public static void main(String[] args) {
		
		SetGoogleMapDetails ob = new SetGoogleMapDetails();
		ob.setAccuracy(50);
		ob.setName("Frontline house");
		ob.setAddress("29, side layout, cohen 09");
		ob.setPhone_number("(+91) 983 893 3937");
		ob.setWebsite("http://google.com");
		ob.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		ob.setTypes(myList);
		
		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		ob.setLocation(lc);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res=given().queryParam("key","qaclick123")
		.body(ob).log().all()
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);
		
	}
}
