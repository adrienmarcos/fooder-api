package com.evereats.fooder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.evereats.fooder.*")
public class FooderApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FooderApiApplication.class, args);
	}
}
