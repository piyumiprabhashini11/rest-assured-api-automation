
public class POJO {

	//POJO(Plain Old Java Object) classes are used for serialization an deseralilization
	//Serialization - Converting a java object into a request body(Jason or xml Payload)
	//Deserialization - Converting a response body into a java object
	
	//Below given is an example of Serialization
	
	/*JSON object
	   {
	     "message":"Hello",
	     "greetings": "Hi"
	  }
	  */
	
	//First need to create a private variable for each key of the JSON object (message,greetings)
	
	private String message;
	private String greetings;
	
	//Then implement getters and setters for each related value of the variables
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
	

	public String getGreetings() {
		return greetings;
	}
	
    
	public void setGreetings(String greetings) {
		this.greetings=greetings;
	}
	
	
	public static void main(String[] args) {
		
	//Create Java Object
		POJO p=new POJO();
		p.setMessage("Hello");
		p.setGreetings("Hi");
		
		}

	
	//Finally pass the created java object into the api request body/payload as shown below
	
	 given().body(p)
	.when().post("/message")
	.then().statusCode(201);
	
	

	
}
