package yao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestService {
  private Logger log = LoggerFactory.getLogger(getClass());

  private String servicePath = "/hello";

  @Autowired
  private DiscoveryClient discoveryClient;

  private RestTemplate rest = new RestTemplate();

  @Autowired
  private RestTemplate eurekaRest;

  @Value("${serviceId}")
  private String serviceId;

  @RequestMapping("/route")
  public String route() {
    log.info("serviceId: {}", serviceId);
    List<ServiceInstance> ls = discoveryClient.getInstances(serviceId);
    ServiceInstance first = ls.get(0);

    // Call REST with URL
    String url = first.getUri().toString();
    log.info("service instance URL: {}", url);
    String restServiceUrl = url + servicePath;
    log.info("REST service URL: {}", restServiceUrl);
    log.info("REST response: {}", rest.getForObject(restServiceUrl, String.class));

    // Call REST with serviceId
    String serviceIdUrl = "http://" + serviceId + servicePath;
    log.info("serviceId URL: {}", serviceIdUrl);
    return eurekaRest.getForObject(serviceIdUrl, String.class);
  }
}
