package demo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.Payload;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicJson {

    // static ensures the same bookIds list is used by addBook() and deleteBook()
    // even if TestNG creates different objects for each test method.
	//endpoint=https://rahulshettyacademy.com/Library/Addbook.php
    
	static List<String> bookIds = new ArrayList<>();
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		JsonPath response=given().header("Content-Type","application/json").
		body(Payload.AddBook(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response().jsonPath();	
		String id=response.getString("ID");
//		System.out.println("ID= "+id);	
		bookIds.add(id);
				
}
	
	@DataProvider(name="BooksData")
	public Object[][] getBooksData() {
		//array=collection of elements
		//multidimentional array=collection of arrays
		return new Object[][] {
			{"aaa","1000"},{"bbb","2000"},
			{"ccc","3000"},{"ddd","4000"},
			{"eee","5000"},{"fff","6000"},
			};
		}
	
	//Both A. and B. methods can be used to delete Books in the API by Id 
	//A.
	@Test
	public void deleteBook() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		for(String id:bookIds) {
			given().log().all().body(Payload.deleteBookByID(id)).
			when().post("/Library/DeleteBook.php").
			then().assertThat().statusCode(200);
	    }
			
	}
	
    //B.
    @Test(dependsOnMethods="addBook",dataProvider="BooksData")
	public void deleteBook(String isbn,String aisle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().body(Payload.deleteBookByID(isbn+aisle)).
		when().post("/Library/DeleteBook.php").
		then().assertThat().statusCode(200);
		}
	
}

