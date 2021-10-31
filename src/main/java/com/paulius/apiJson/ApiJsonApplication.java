package com.paulius.apiJson;

import com.paulius.apiJson.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ApiJsonApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiJsonApplication.class, args);
	}
}
