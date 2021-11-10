package edu.miu.cs.cs544.springrest;

import edu.miu.cs.cs544.springrest.service.RegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RegistrationService service = context.getBean(RegistrationService.class);
		service.initialize();
	}

}
