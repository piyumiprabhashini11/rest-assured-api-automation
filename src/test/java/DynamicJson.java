import org.testng.annotations.Test;

import files.Payload;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test
	public void addBook() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		JsonPath response=given().log().all().header("Content-Type","application/json").
		body(Payload.AddBook("adad","7595")).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().jsonPath();	
		String id=response.getString("ID");
		System.out.println("ID= "+id);
			
}
}