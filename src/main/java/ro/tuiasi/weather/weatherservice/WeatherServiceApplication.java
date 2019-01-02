package ro.tuiasi.weather.weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.tuiasi.weather.weatherservice.FileUtils.FileUtils;
import ro.tuiasi.weather.weatherservice.HttpUtils.HttpUtils;
import ro.tuiasi.weather.weatherservice.openWeatherMap.Response;

import java.text.SimpleDateFormat;
import java.time.Instant;

@SpringBootApplication
@EnableScheduling
public class WeatherServiceApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(WeatherServiceApplication.class).web(WebApplicationType.NONE).run(args);
  }

  @Value("${filePath}")
  private String filePath;
  @Autowired
  private FileUtils fileUtils;
  @Autowired
  private HttpUtils httpUtils;

  @Scheduled(fixedDelayString = "${scheduler.one.miliseconds}")
  private void schedulerOne() {

    Response response = httpUtils.getCityWeather();

    SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
    String time = time_formatter.format(System.currentTimeMillis());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormat.format(System.currentTimeMillis());
    String unixTimestamp = String.valueOf(Instant.now().getEpochSecond());

    String temp = String.valueOf(response.getMain().getTemp());
    String temp_min = String.valueOf(response.getMain().getTempMin());
    String temp_max = String.valueOf(response.getMain().getTempMax());
    String pressure = String.valueOf(response.getMain().getPressure());
    String humidity = String.valueOf(response.getMain().getHumidity());

    String wind_speed = String.valueOf(response.getWind().getSpeed());
    String wind_degree = String.valueOf(response.getWind().getDeg());

    String clouds = String.valueOf(response.getClouds().getAll());

    String api_date = String.valueOf(response.getDt());

    String visibility = String.valueOf(response.getVisibility());

    String weather = response.getWeather().get(0).getMain();
    String weather_description = response.getWeather().get(0).getDescription();

    String city = response.getName();

    String[] text = {time, unixTimestamp, temp, temp_min, temp_max, pressure, humidity, wind_speed, wind_degree,
            clouds, api_date, visibility, weather, weather_description,city};
    fileUtils.writeToCsvFile(text, filePath + date + ".csv");
  }

}

