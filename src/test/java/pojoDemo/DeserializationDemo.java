package pojoDemo;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojoDeserialization.API;
import pojoDeserialization.GetCourse;
import pojoDeserialization.Mobile;
import pojoDeserialization.WebAutomation;

public class DeserializationDemo {

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
		
		 JsonPath js=new JsonPath(response);
		 String accessToken=js.getString("access_token");
		 System.out.println("The response is"+response);
		
		//To Get course details by providing the generated access token
		/* the Java Object 'gc' of type 'GetCourse' will be created.(This is from GetCourse.java class)
		 * as.(GetCourse.class) converts Json to a Java POJO Object;
		*  Jackson (Databind) library will deserialize the json to a java pojo objet*/
		GetCourse gc=given().queryParam("access_token", accessToken)
		.when().get("/oauthapi/getCourseDetails")
		.then().extract().as(GetCourse.class);;
		/*as(GetCourse.class) is used to get all the objects of the OAuth json response through the getters(get methods) implemented inside the 'GetCourse.java' class
		This is called as 'Deserialization*/
		System.out.println("The LinkedIn link is = "+gc.getLinkedIn()); //This will return the value of LinkedIn object
		System.out.println("Title of the second course of API courses is = "+gc.getCourses().getApi().get(1).getCourseTitle());//This will return the course title of the second course inside API courses array
		System.out.println("Price of the second course of API courses is = "+gc.getCourses().getApi().get(1).getPrice()); //This will return the course price of the second course inside API courses array
		
		//To get go through the all elements in API array/course inside the Courses object of the OAuth Json and get the price of the API course with title "SoapUI Webservices testing"
		List<API> apiCourses =gc.getCourses().getApi(); //The data type of getApi() method which is declared in 'Courses' class is 'List<API>'
		
		//Both of the below ways can be used (Method B. is the industry standard way)
		
		//A.
		for(int i=0;i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("Price of the course 'SoapUI Webservices testing' of API courses is = "+apiCourses.get(i).getPrice()); 
			}
		}
		
		//B.
		for(API apiCourse:apiCourses) {
			if(apiCourse.getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("Price of the course 'SoapUI Webservices testing' of API courses is = "+apiCourse.getPrice()); 
			}
		}
		
		//To print all course titles of all webAutomation courses (Both method A. and B. can be used)
		List<WebAutomation> webAutomationCourses=gc.getCourses().getWebAutomation();
		
		//A.
		for(WebAutomation webAutomationCourseName:webAutomationCourses) {
			System.out.println(webAutomationCourseName.getCourseTitle());
		}
		
		//B.
		for(int i=0;i< webAutomationCourses.size();i++) {
			
			System.out.println(webAutomationCourses.get(i).getCourseTitle());
			
		}
		
		//To print only course titles of 1st and 3rd webAutomation courses
		for (int i = 0; i < webAutomationCourses.size(); i++) {
		    if (i == 0 || i == 2) {
		        System.out.println(webAutomationCourses.get(i).getCourseTitle());
		    }
		}
		
		//To print 1st and last webAutomation course titles 	
		int size = webAutomationCourses.size(); // if(size > 0) is used to avoid IndexOutOfBoundsException
		if (size > 0) {
		    System.out.println(webAutomationCourses.get(0).getCourseTitle());          // First
		    System.out.println(webAutomationCourses.get(size - 1).getCourseTitle());   // Last
		}
		
		//To compare expected list of course titles with the actual course titles
		
		//1st Make an Array to insert all the expected course names
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Cypress"};
		
		//Convert this Array to an ArrayList (This is for the convenience of comparing two ArrayLists)
		List<String> expectedList=Arrays.asList(courseTitles); //To convert the Array to an ArrayList
		
		
		//2nd make an Arraylist by inserting all actual course titles included in the API
		List<WebAutomation> w=gc.getCourses().getWebAutomation();
		List<String> a = new ArrayList<>();
		
		for(int i=0;i<w.size();i++) {
			
			a.add(w.get(i).getCourseTitle());	
		}
		
		//To check whether the actual list(a) equals the expected list(expectedList)
		Assert.assertTrue( a.equals(expectedList));
		
		
	}
}
