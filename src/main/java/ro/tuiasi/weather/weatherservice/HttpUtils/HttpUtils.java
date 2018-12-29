package ro.tuiasi.weather.weatherservice.HttpUtils;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ro.tuiasi.weather.weatherservice.openWeatherMap.Response;

import java.util.logging.Logger;

public class HttpUtils {

  private Logger logger = Logger.getLogger(HttpUtils.class.getName());

  public String getCityWeather() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response
            = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=Iasi,ro&units=metric&APPID=d05788dec737f6842982d32a83c9cebf", String.class);
    logger.info(response.getBody());
    return response.getBody();
  }
}
