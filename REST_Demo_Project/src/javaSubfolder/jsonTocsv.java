
//This class has methods for
//Taking input as String(json) and parsing it to a new csv file

package javaSubfolder;
import java.io.File;
import java.io.IOException;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.FileUtils;

public class jsonTocsv {
	
	public static void methodToConvertJSONToCSVFile(String jsonString) throws IOException
	{
		//create a jsonNode from the response string
		ObjectMapper mapper = new ObjectMapper();
	    JsonNode jsonNodeObject = mapper.readTree(jsonString);
	    

	    CsvSchema.Builder builder = CsvSchema.builder()
	        .addColumn("userId")
	        .addColumn("id")
	        .addColumn("title")
	        .addColumn("body");
	 
	    CsvSchema csvSchema = builder.build().withHeader();
	 
	    CsvMapper csvMapper = new CsvMapper();
	   // csvMapper.configure(Feature.IGNORE_UNKNOWN, true);
	    csvMapper.writerFor(JsonNode.class)
	        .with(csvSchema)
	        .writeValue(new File("C:\\Users\\218995\\git\\apiAutomation\\REST_Demo_Project\\test-output\\writtenCSVDataFile.txt"), jsonNodeObject);
	}
	
	
	
	
	//trial method
	@SuppressWarnings("deprecation")
	public static void methodToConvertJSONToCSV_trial(String jsonString)
	{
		 JSONObject output;
	      try {
	         output = new JSONObject(jsonString);
	         JSONArray docs = output.getJSONArray("fileName");
	         File file = new File("output.txt");
	         String csv = CDL.toString(docs);
	         FileUtils.writeStringToFile(file, csv);
	         System.out.println("Data has been Sucessfully Writeen to "+ file);
	         System.out.println(csv);
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	}

	
	
}
