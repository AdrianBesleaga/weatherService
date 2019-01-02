package ro.tuiasi.weather.weatherservice.FileUtils;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileUtils {
  public void writeToCsvFile(String[] text, String filePath){
    File file = new File(filePath);
    try {
      // create FileWriter object with file as parameter
      FileWriter outputfile = new FileWriter(file, true);

      // create CSVWriter object filewriter object as parameter
      CSVWriter writer = new CSVWriter(outputfile);

      // adding header to csv
      if(file.length() == 0) {
        String[] header = {"Date","TimeStamp","Temp", "Temp_Min", "Temp_Max", "Pressure","Humidity",
                "Wind_Speed","Wind_Degree","Clouds","Api_Timestamp","Visibility","Weather","Weather_Description","City"};
        writer.writeNext(header);
      }

      writer.writeNext(text);

      // closing writer connection
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
