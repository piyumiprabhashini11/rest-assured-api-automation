package demo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;

public class PassingStaticJsonFilesDirectlyIntoPostmethods {

	public static void main(String[] args) {
		//endpoint=https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
		//How to send a static Json file(payload) directly into the POST method
		//First need to convert the content of the file to String; For that,
		//Convert content inside the Jason file -> To Byte by using the command Files.readAllBytes(Paths.get("file location path"))-> To String by using new String()
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		try {
			String response=given().log().all().queryParam("key", "qaclick123")
			.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\rupiy\\eclipse-workspace\\rest-assured-api-automation\\src\\test\\resources\\addPlace.json"))))
			.when().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200)
			.body("scope",equalTo("APP")).extract().response().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

}
