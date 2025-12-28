package demo;
import files.Payload;
import io.restassured.path.json.JsonPath;


public class ComplexJasonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Refer the Json object of Section 6 - 25 part to answer all of the below questions
       JsonPath js=new JsonPath(Payload.coursePrice());
       
       //1.	Print No of courses returned by API
       System.out.println(); 
       System.out.println("1. Print No of courses returned by API");
       int noOfCourses=js.getInt("courses.size()");
       System.out.println("No of courses returned by API= "+noOfCourses);
       
       //To Print the name of the website
       System.out.println(); 
       System.out.println("To Print the name of the website");
       String webSitename=js.getString("dashboard.website");
       System.out.println("Name of the website is "+webSitename);
      
       //2. Print Purchase Amount
       System.out.println(); 
       System.out.println("2. Print Purchase Amount");
       int purchaseAmount =js.getInt("dashboard.purchaseAmount");
       System.out.println("The purchase amount is "+purchaseAmount);
       
       //3. Print Title of the first course
       System.out.println(); 
       System.out.println("3. Print Title of the first course");
       String titleOfFirstCorse=js.getString("courses[0].title");
       System.out.println("The Title of the first course is "+titleOfFirstCorse);
       
       //To Print the price of the second course
       System.out.println();
       System.out.println("To Print the price of the second course");
       int priceOfSecondCourse=js.getInt("courses[1].price");
       System.out.println("The Price of the second course is "+priceOfSecondCourse);
       
       //4. Print All course titles and their respective Prices
       System.out.println(); 
       System.out.println("4. Print All course titles and their respective Prices");
       int count=js.getInt("courses.size()"); //get the size of the courses array; use js.getInt as the returning value is an integer
       for(int i=0;i<count;i++) {
    	   System.out.println("The Title of the course "+(i+1)+" is "+js.getString("courses["+i+"].title"));  
    	   System.out.println("The Price of the course "+(i+1)+" is "+js.getInt("courses["+i+"].price")); 
    	   
       }
       
       //5. Print no of copies sold by RPA Course
       System.out.println(); 
       System.out.println("5. Print no of copies sold by RPA Course");
//       System.out.println("No of copies sold by RPA Course = "+js.getInt("courses[2].copies"));
       
       for(int i=0;i<count;i++) {  //int count=js.getInt("courses.size()")
    	  String courseTitle= js.getString("courses["+i+"].title");
    	  if(courseTitle.equals("RPA")) {
    		  System.out.println("No of copies sold by RPA Course ="+js.getInt("courses["+i+"].copies"));
    		  break; // break is used to optimise the code by exiting from the code once condition is fulfilled without continuing the if condition
    		  	  }
       }
//       //To Print Total Price of all RPA course copies
       System.out.println(); 
       System.out.println("To Print no of copies sold by RPA Course");
       int RPAcoursePrice =js.getInt("courses[2].price");
       int noOfRPACopies=js.getInt("courses[2].copies");
       System.out.println("Total Price of all RPA course copies = "+(RPAcoursePrice*noOfRPACopies));
       
       //To Print Sum of all course prices(Total price of all courses' copies)
       System.out.println(); 
       System.out.println("To Print Sum of all course prices(Total price of all courses' copies)");
       int total=0;
       for(int i=0;i<count;i++) {
    	   int coursePrice =js.getInt("courses["+i+"].price");
           int noOfCopies=js.getInt("courses["+i+"].copies");
     	   total= total+(coursePrice*noOfCopies);
       }
       System.out.println("Sum of all course prices = "+total);
  
       //6. Verify if Sum of all Course prices matches with Purchase Amount
           System.out.println(); 
           System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
           int purchasingAmount=js.getInt("dashboard.purchaseAmount"); 
           //Refer above section to get an idea of how the total was derived
               if(total==purchasingAmount) { 
            	   System.out.println("Sum of all Course prices matches with Purchase Amount");  
               }
               else System.out.println("Purchase Amount and the total price is not matching");
       
       
       /*The Output
1. Print No of courses returned by API
No of courses returned by API= 3

To Print the name of the website
Name of the website is rahulshettyacademy.com

2. Print Purchase Amount
The purchase amount is 910

3. Print Title of the first course
The Title of the first course is Selenium Python

To Print the price of the second course
The Price of the second course is 40

4. Print All course titles and their respective Prices
The Title of the course 1 is Selenium Python
The Price of the course 1 is 50
The Title of the course 2 is Cypress
The Price of the course 2 is 40
The Title of the course 3 is RPA
The Price of the course 3 is 45

5. Print no of copies sold by RPA Course
No of copies sold by RPA Course =10

To Print no of copies sold by RPA Course
Total Price of all RPA course copies = 450

To Print Sum of all course prices(Total price of all courses' copies)
Sum of all course prices = 910

6. Verify if Sum of all Course prices matches with Purchase Amount
Sum of all Course prices matches with Purchase Amount

      */
             
	}

}
