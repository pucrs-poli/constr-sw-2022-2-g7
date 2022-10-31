package br.pucrs.classesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClassesMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClassesMsApplication.class, args);
	}
}
