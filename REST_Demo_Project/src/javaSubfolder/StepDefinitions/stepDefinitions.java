package javaSubfolder.StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
		// Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		//throw new io.cucumber.java.PendingException();
	}

	@When("I read input from {string}")
	public void i_read_input_from(String string) {
		// Write code here that turns the phrase above into concrete actions

		requiredJSON = csvToJson.convertListOfMapToJsonString(csvToJson.methodToConvertCSVToJSON(string));
		//throw new io.cucumber.java.PendingException();
	}

	@When("I submit POST request")
	public void i_submit_post_request() {
		// Write code here that turns the phrase above into concrete actions
		
		String responseString = given().log().all()
				.header("Content-Type", "application/json").body(requiredJSON).when()
				.post("/create").then().assertThat().statusCode(200).extract().response().asPrettyString();

		System.out.println("Response Body as String:" + responseString);
		JsonPath jsonObject = new JsonPath(responseString);// create jsonPath object to handle the json body response &
															// pass the response String as param
		String statusInResponse = jsonObject.getJsonObject("status"); // to get the certain json parameter value by providing
																// key as param
		System.out.println("Place ID is " + statusInResponse);
		//throw new io.cucumber.java.PendingException();
	}

	@Then("I should see response status code {string}")
	public void i_should_see_response_status_code(String string) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("****As of now not implemented****");
		//throw new io.cucumber.java.PendingException();
	}
	
	
	//GET request step definitions
	@When("I submit  GET request")
	public void i_submit_get_request() {
		GET_ResponseJSON=given()
		.when().log().all().get("/api/v1/employee/1").then().body("message", equalTo("Successfully! Record has been fetched."))
		.extract().response().asPrettyString();
	}

	@Then("I should see response status code {string} for GET request")
	public void i_should_see_response_status_code_for_get_request(String string) {
		given()
		.when().log().all().get("/api/v1/employee/1").then().assertThat().statusCode(200)
		.extract().response().asPrettyString();
	}

	
	
	@Then("I should retrieve json response in csv file")
	public void i_should_retrieve_json_response_in_csv_file() {
		jsonTocsv.methodToConvertJSONToCSV(GET_ResponseJSON);
	}
}
