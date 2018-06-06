/**
 * 
 */
/**
 * @author lvzefeng@sinoiov.com
 *
 */
package com.charhoo.shop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class StartApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(StartApplication.class).run(args);
	}
}