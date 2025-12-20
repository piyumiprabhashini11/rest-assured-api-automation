import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PassingStaticJsonFilesDirectlyIntoPostmethods {

	public static void main(String[] args) {
		//How to send a static Json file(payload) directly into the POST method
		//First need to convert the content of the file to String; For that,
		//Convert content in the file -> To Byte -> To String
		try {
			String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/jason")
			.body(Files.readAllBytes(Paths.get("C:\\Users\\rupiy\\eclipse-workspace\\rest-assured-api-automation\\src\\test\\resources\\addPlace.json")))
			.when().post("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200)
			.body("scope",equalTo("APP")).extract().response().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
