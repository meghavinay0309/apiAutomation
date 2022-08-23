import java.io.*;

import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

public class csvToJson {
	
	public static List<Map<?, ?>> methodToConvertCSVToJSON(String fileName)
	{
		List<Map<?, ?>> list=new ArrayList<>();
		File input = new File("+fileNAme+");
		// System.out.println(new File(".").getAbsolutePath()); //This line will tell you what the current directory is:
		try {
			CsvSchema csv = CsvSchema.emptySchema().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader().forType(Map.class).with(csv)
					.readValues(input);
			//List<Map<?, ?>> list = mappingIterator.readAll();
			list=mappingIterator.readAll();
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
