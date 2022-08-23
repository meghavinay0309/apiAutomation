import java.io.File;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.FileUtils;

public class jsonTocsv {
	
	@SuppressWarnings("deprecation")
	public static void methodToConvertJSONToCSV(String jsonString)
	{
		 JSONObject output;
	      try {
	         output = new JSONObject(jsonString);
	         JSONArray docs = output.getJSONArray("fileName");
	         File file = new File("freshCSV.txt");
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
