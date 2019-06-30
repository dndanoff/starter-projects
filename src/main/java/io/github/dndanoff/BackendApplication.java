package io.github.dndanoff;


import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@PreDestroy
	public void cleanUp() {
		rx.schedulers.Schedulers.shutdown();
	}

}
