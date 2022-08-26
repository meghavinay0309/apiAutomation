package javaSubfolder.StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import files.payload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import javaSubfolder.RequestBuilder;
import javaSubfolder.booksPOJO;
import javaSubfolder.csvToJson;
import javaSubfolder.jsonTocsv;
import javaSubfolder.listOfMapsToPOJO;



public class stepDefinitions {
	
	private String requiredJSON;
	private String GET_ResponseJSON;
	
	//POST request using CSV data & POJO
	private List<Map<?, ?>> csvToJSONListOfMaps;
	booksPOJO booksPOJOObject=new booksPOJO();
	
	@Given("I am authorized user")
	public void i_am_authorized_user() {
	//	RestAssured.baseURI = "https://dummy.restapiexample.com/";	
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}

	@When("I read input from {string}")
	public void i_read_input_from(String string) {
		requiredJSON = csvToJson.convertListOfMapToJsonString(csvToJson.methodToConvertCSVToJSON(string));
		System.out.println("******************************Printing constructed JSON************************************"+ requiredJSON);
	}

	@When("I submit POST request")
	public void i_submit_post_request() {
			
		String responseString = given().log().all()
				.header("Content-Type", "application/json")
				.body(requiredJSON)
				.when().post("/posts")
				.then().assertThat().statusCode(201).extract().response().asPrettyString();

		System.out.println("Response Body as String:" + responseString);
		JsonPath jsonObject = new JsonPath(responseString);// create jsonPath object to handle the json body response &
															// pass the response String as param
		int statusInResponse = jsonObject.getJsonObject("id"); // to get the certain json parameter value by providing
																// key as param
		System.out.println("Unique ID is " + statusInResponse);
		
	}

	@Then("I should see response status code {string}")
	public void i_should_see_response_status_code(String string) {
		
		System.out.println("****As of now not implemented****\n");
		
	}
	
	
	//GET request step definitions
	
	@Given("I am authorized user to execute GET request")
	public void i_am_authorized_user_to_execute_get_request() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
	
	@When("I submit  GET request")
	public void i_submit_get_request() {
		GET_ResponseJSON=given()
		.when().log().all().get("/posts/1")
		.then().body("body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"))
		.extract().response().asPrettyString();
	}

	@Then("I should see response status code {string} for GET request")
	public void i_should_see_response_status_code_for_get_request(String string) {
		given()
		.when().log().all().get("/posts/1").then().assertThat().statusCode(200)
		.extract().response().asPrettyString();
	}

	
	
	@Then("I should retrieve json response in csv file")
	public void i_should_retrieve_json_response_in_csv_file() throws IOException {
		jsonTocsv.methodToConvertJSONToCSVFile(GET_ResponseJSON);
	}
	
	//POST request with CSV data & POJO class
	
	@Given("I am authorized user for the {string}")
	public void i_am_authorized_user_for_the(String baseURI) {
		RestAssured.baseURI =baseURI;
	    
	}

	@When("I read data from {string} file")
	public void i_read_data_from_file(String string) {
		//requiredJSON = csvToJson.convertListOfMapToJsonString(csvToJson.methodToConvertCSVToJSON(string));
		System.out.println("******************************Printing constructed JSON************************************"+ requiredJSON);
		
		//Calling method to convert csv data to list of maps
		csvToJSONListOfMaps=csvToJson.methodToConvertCSVToJSON(string);
		System.out.println("%%%%%%%Printing converted List of Maps%%%%%%%%%"+csvToJSONListOfMaps );
		
		//New POJO class object to store returned POJO object
		
		booksPOJOObject= listOfMapsToPOJO.methodToConvertListOfMapsToPOJO(csvToJSONListOfMaps);
		
	}

	@When("I have generated pojo class object")
	public void i_have_generated_pojo_class_object() {
	   //calling getter methods
		System.out.println("##USER ID from POJO##\n" +booksPOJOObject.getUserID());
		System.out.println("##IT from POJO##\n" +booksPOJOObject.getIt());
		System.out.println("##Title from POJO##\n" +booksPOJOObject.getTitle());
		System.out.println("##Body from POJO##\n" +booksPOJOObject.getBody());
		
	}

	@When("I submit POST request using pojo class")
	public void i_submit_post_request_using_pojo_class() {
		//calling method which builds Json string from the POJO class
		String jsonStringFromPOJO=RequestBuilder.methodToBuildJsonFromPOJO(booksPOJOObject);
		
		//Sending POST request
		String responseString = given().log().all()
									.header("Content-Type", "application/json")
									.body(jsonStringFromPOJO)
				                .when().post("/posts")
				                .then().assertThat().statusCode(201).extract().response().asPrettyString();

		System.out.println("Response Body as String:" + responseString);
		JsonPath jsonObject = new JsonPath(responseString);// create jsonPath object to handle the json body response &
															// pass the response String as param
		int statusInResponse = jsonObject.getJsonObject("id"); // to get the certain json parameter value by providing
																// key as param
		System.out.println("Unique ID is " + statusInResponse);
		
	}

	@Then("I should see Unique ID")
	public void i_should_see_unique_id() {
	    
	}

}
