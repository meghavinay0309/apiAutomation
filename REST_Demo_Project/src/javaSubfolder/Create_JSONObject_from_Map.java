

//********************Practice code********************
//********************Not related to Framework********************

//This class demonstrates  
// 1. Creating a Map object 
// 2. Converting that Map object to a JSON object

package javaSubfolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class Create_JSONObject_from_Map {
	
	public static void convertMapToJson() {
        Map<String, String> elements = new HashMap();
        
        //To retain the order, we need to use SortedMap instead.
        //SortedMap<String, String> elements = new TreeMap();
        elements.put("Name", "Megha");
        elements.put("City", "San Diego");
        elements.put("Company", "NTT Data");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(elements);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		convertMapToJson();
		

	}

}
