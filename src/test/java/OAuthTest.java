import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//To request the access token from Authentication Server by giving client id and client secret under grant type client credentials
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=  given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all().post("/oauthapi/oauth2/resourceOwner/token")
		.then().extract().response().asString();
		
		System.out.println("The response is"+response);
		JsonPath js=new JsonPath(response);
		String accessToken=js.getString("access_token");
		
		//To Get course details by providing the generated access token
		given().queryParam("access_token", accessToken)
		.when().get("/oauthapi/getCourseDetails")
		.then().log().all();
		}
}
