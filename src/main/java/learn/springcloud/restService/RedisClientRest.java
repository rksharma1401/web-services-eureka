package learn.springcloud.restService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@RestController
@Slf4j
public class RedisClientRest {

	 @PostMapping("/redis/add")
	    public String addData(){
		 try (Jedis jedis = new Jedis("redis-18420.c93.us-east-1-3.ec2.cloud.redislabs.com", 18420,300000)) {
				jedis.auth("z5hz6pFSicuekDLRWDifPC9fnkpRQVj7");

				
				jedis.set("events/city/rome", "32,15,223,828");
				String cachedResponse = jedis.get("events/city/rome");
				log.error(" return cachedResponse {}",cachedResponse);
				 return cachedResponse;
			}
	 }
}
