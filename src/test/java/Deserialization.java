
public class Deserialization {
	
	//Deserialization - Converting a response body into a java object
	
		//Below given is an example of Serialization
		
		/*JSON object
		   {
		     "message":"Hello",
		     "greetings": "Hi"
		  }
		  */
	
	//First need to create private variables for each key of the JSON object (message,greetings)
	 private String message;
	 private String greetings;
	 
	//Then implement getters and setters for each variable
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
		// TODO Auto-generated method stub
		
		//Create Java Object
		 Deserialization d=new Deserialization();
		 d.getMessage();
		 d.getGreetings();
		 //After calling getters,we will get output varaiblae values of the response
//		 Ex:- Hello
//		      Hi
		 
    }

}
