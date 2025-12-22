import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;

public class JiraIssuesManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create an issue/bug in Jira
		//endpoint=https://piyumimadduamge1700.atlassian.net/rest/api/3/issue
		RestAssured.baseURI="https://piyumimadduamge1700.atlassian.net";
		JsonPath response=given().body(Payload.createJiraIssue()).header("Content-Type","application/json").
	    auth().preemptive().basic("piyumimadduamge1700@gmail.com","Paste the REAL_API_TOKEN here").
		when().post("/rest/api/3/issue").
		then().log().all().statusCode(201).
		extract().response().jsonPath();
		String issueId=response.getString("id");
		
		//Add File attachment
		//endpoint=https://piyumimadduamge1700.atlassian.net/rest/api/3/issue/{issueid}/attachments
		//header("X-Atlassian-Token","no-check") is given in file attachment requests to make the api requests without forbidden from Jira.
		//“X-Atlassian-Token: no-check disables Jira’s CSRF validation for attachment uploads; without it, Jira returns 403 Forbidden.”
		//multiPart method is used to add an attachment (in this example, attaching the .png file)
		//Here,the {key} is given in the post method and the value for that key is given inside the 'PathParam by assigning the issue id value to it.
		//{key} in post("/rest/api/3/issue/{key}/attachments") = issueId (pathParam("key",issueId))
		given().header("X-Atlassian-Token","no-check").
		pathParam("key",issueId).
		auth().preemptive().basic("piyumimadduamge1700@gmail.com","Paste the REAL_API_TOKEN here").
		multiPart("file",new File("C:\\Users\\rupiy\\eclipse-workspace\\rest-assured-api-automation\\src\\test\\resources\\jira-cloud-platform.png")).
		when().post("/rest/api/3/issue/{key}/attachments").
		then().log().all().assertThat().statusCode(200);
		
	}
		
}
