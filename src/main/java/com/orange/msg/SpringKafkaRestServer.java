package com.orange.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry class for rest server.
 */
@SpringBootApplication
public class SpringKafkaRestServer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringKafkaRestServer.class, args);
	}
}
