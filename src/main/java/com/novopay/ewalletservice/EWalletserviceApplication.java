package com.novopay.ewalletservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaRepositories
public class EWalletserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EWalletserviceApplication.class, args);
	}

}
