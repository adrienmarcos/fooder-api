package com.evereats.fooder;

import com.evereats.fooder.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class FooderApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FooderApiApplication.class, args);
	}
}
