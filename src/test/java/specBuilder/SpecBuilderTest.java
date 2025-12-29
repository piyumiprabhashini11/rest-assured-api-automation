package specBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoSerialization.Location;
import pojoSerialization.SetGoogleMapDetails;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {
	
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
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").build();
		RequestSpecification req=given().spec(reqSpec).body(ob).log().all();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
	    
		Response response=req.when().post("/maps/api/place/add/json")
		.then().assertThat().spec(resSpec).extract().response();
		
		String responseString=response.asString();
		System.out.println(responseString);
		
	}
}
