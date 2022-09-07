
//********************Practice code********************
//********************Not related to Framework********************

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import files.payload;
import static io.restassured.RestAssured.*;  //import all static packages from RestAssured in order to use them in code

public class basicClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//provide Base URI
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Given - provide query param and header
		//When - submitting the request like POST etc
		//Then - Validate the response like using assertThat().statusCode() etc
		
		//Use log().all() iwith Given() and Then() to log the details
		
		//(1)to post the request(add place) by providing query param,header,target resource,request body
		//and validate response body, fetch certain response json value(placeID value)
		String responseString=given().log().all()
									 .queryParam("key","qaclick123")
									 .header("Content-Type","application/json")
									 .body(payload.addPlace())
							  .when().post("/maps/api/place/add/json")
							  .then().assertThat().statusCode(200)
							  .body("scope", equalTo("APP"))
							  .header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asPrettyString();
		
		
		System.out.println("Response Body as String:" + responseString); 
		JsonPath jsonObject=new JsonPath(responseString);//create jsonPath object to handle the json body response & pass the response String as param
		String placeID=jsonObject.getJsonObject("place_id"); // to get the certain json parameter value by providing key as param
		System.out.println("Place ID is "+ placeID);
		
		//(2)to update the address to the placeID which we got from POST request
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\n"
				+ "\"place_id\":\""+placeID+"\",\n"
				+ "\"address\":\"70 Summer walk, USA\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
       //(3)to verify if address is updated in previous step by sending GET request with same place_id
		given().log().all().queryParam("key","qaclick123").queryParam("place_id", "6a30b840957f5da435072890cb34b20f")
		.when().log().all().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
		.body("address", equalTo("70 Summer walk, USA"));
		
		System.out.println("First script is complete");
	}

}
