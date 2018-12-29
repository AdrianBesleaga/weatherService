package ro.tuiasi.weather.weatherservice;

import com.github.opendevl.JFlat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.tuiasi.weather.weatherservice.HttpUtils.HttpUtils;
import ro.tuiasi.weather.weatherservice.openWeatherMap.Response;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

//@SpringBootApplication
public class WeatherServiceApplication {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		HttpUtils httpUtils = new HttpUtils();
		String response = httpUtils.getCityWeather();
//		SpringApplication.run(WeatherServiceApplication.class, args);

		JFlat flatMe = new JFlat(response);

//get the 2D representation of JSON document
		List<Object[]> json2csv = flatMe.json2Sheet().getJsonAsSheet();

//write the 2D representation in csv format
		flatMe.write2csv("C:\\Users\\Adrian\\Desktop\\Weather\\weather.csv");

	}

}

