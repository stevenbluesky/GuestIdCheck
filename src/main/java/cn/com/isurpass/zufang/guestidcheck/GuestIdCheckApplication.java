package cn.com.isurpass.zufang.guestidcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GuestIdCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestIdCheckApplication.class, args);
	}
}
