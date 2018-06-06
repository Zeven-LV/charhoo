package com.charhoo.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.charhoo.os"})
public class RedisosStarter {

	public static void main(String[] args) {
		SpringApplication.run(RedisosStarter.class, args);
	}
}
