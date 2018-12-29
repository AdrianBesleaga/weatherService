package ro.tuiasi.weather.weatherservice.FileUtils;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
  private void writeToCsvFile(String[] text, String filePath){
    File file = new File(filePath);
    try {
      // create FileWriter object with file as parameter
      FileWriter outputfile = new FileWriter(file);

      // create CSVWriter object filewriter object as parameter
      CSVWriter writer = new CSVWriter(outputfile);

      // adding header to csv
      if(file.length() == 0) {
        String[] header = {"Name", "Class", "Marks"};
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
