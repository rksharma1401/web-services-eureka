package learn.springcloud.configclient;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
  

@SpringBootApplication
public class ConfigclientApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(ConfigclientApplication.class, args);
	}

} 


@RestController
class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	 

	// client to see if app registered or not
	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}
	@Value("#{servletContext.contextPath}")
    private String servletContextPath;

	@RequestMapping("/getUrl")
	public String getUrl() {
		return servletContextPath;
	}
  
}