package ro.tuiasi.weather.weatherservice.HttpUtils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.tuiasi.weather.weatherservice.openWeatherMap.Response;

import java.util.logging.Logger;

@Component
public class HttpUtils {

  private Logger logger = Logger.getLogger(HttpUtils.class.getName());

  @Value("${api.key}")
  private String apiKey;

  public Response getCityWeather() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Response> response
            = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=Iasi,ro&units=metric&APPID="+apiKey, Response.class);
    logger.info(response.getBody().toString());
    return response.getBody();
  }
}
