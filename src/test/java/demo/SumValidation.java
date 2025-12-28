package demo;
import org.testng.Assert;
import org.testng.annotations.Test;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public static void validateSUmofCourses() {
		
		JsonPath js=new JsonPath(Payload.coursePrice());
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		int total=0;
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++) {
			int noOfCopies=js.getInt("courses["+i+"].copies");
			int coursePrice=js.getInt("courses["+i+"].price");
		    total =total+(noOfCopies*coursePrice);
		}
		Assert.assertEquals(total, purchaseAmount);
	}
	
}
