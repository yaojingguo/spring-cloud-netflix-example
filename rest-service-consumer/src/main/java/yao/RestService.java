package yao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestService {
  private Logger log = LoggerFactory.getLogger(getClass());
  
  @Value("${zuul}")
  private String zuul;
  
  @Value("${serviceId}")
  private String serviceId;
  
  private RestTemplate rest = new RestTemplate();

  @RequestMapping("/greet")
  public String greet() {
    String url = zuul + "/" + serviceId + "/hello";
    String response = rest.getForObject(url, String.class);
    log.info("service response: {}", response);
    return response;
  }
}
