package javaSubfolder.StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import files.payload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import javaSubfolder.csvToJson;
import javaSubfolder.jsonTocsv;



public class stepDefinitions {
	
	private String requiredJSON;
	private String GET_ResponseJSON;
	
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
				.header("Content-Type", "application/json").body(requiredJSON)
				.when()
				.post("/posts")
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
		// Write code here that turns the phrase above into concrete actions
		System.out.println("****As of now not implemented****\n");
		//throw new io.cucumber.java.PendingException();
	}
	
	
	//GET request step definitions
	
	@Given("I am authorized user to execute GET request")
	public void i_am_authorized_user_to_execute_get_request() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
	
	@When("I submit  GET request")
	public void i_submit_get_request() {
		GET_ResponseJSON=given()
		.when().log().all().get("/posts/1").then().body("body", equalTo("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"))
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
}
