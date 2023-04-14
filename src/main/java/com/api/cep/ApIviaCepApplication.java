package com.api.cep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ExcludeFromJacocoGeneratedReport
public class ApIviaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApIviaCepApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
