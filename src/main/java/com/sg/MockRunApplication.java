package com.sg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MockRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockRunApplication.class, args);

		//ConfigurableApplicationContext context =
//		for(String s : context.getBeanDefinitionNames()){
//			System.out.println(s);
//		}

	}

}
