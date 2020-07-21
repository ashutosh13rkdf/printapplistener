package com.print.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class PrintListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintListenerApplication.class, args);
	}

}
