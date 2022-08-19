
// This Class demonstrates 
// 1. Submitting POST request using payload class for JSON body
// 2. Verify status code, header from the Response body
// 3. Takes the response JSON String and converts it to Map object

package javaSubfolder;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import files.payload;
import static io.restassured.RestAssured.*;  //import all static packages from RestAssured in order to use them in code
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
public class JSON_Response_to_Map {

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
		String responseString=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.addPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asPrettyString();
		
		
		System.out.println("Response Body as String:" + responseString); 
		JsonPath jsonObject=new JsonPath(responseString);//create jsonPath object to handle the json body response & pass the response String as param
		
		String placeID=jsonObject.getJsonObject("place_id"); // to get the certain json parameter value by providing key as param
		System.out.println("Place ID is "+ placeID);
		
		//Converting JSON to Map
		ObjectMapper mapper = new ObjectMapper();
		try {

            // convert JSON string to Map
            Map<String, String> map = mapper.readValue(responseString, Map.class);
          
			// it works
            //Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

            System.out.println("After converting JSON response string to Map    " +map);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
