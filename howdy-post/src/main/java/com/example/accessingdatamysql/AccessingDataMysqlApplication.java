package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Status;
import com.example.accessingdatamysql.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StatusRepository statusRepository){
		return (args -> {
			statusRepository.save(new Status("status1",1));
			statusRepository.save(new Status("status2",2));
			statusRepository.save(new Status("status3",3));
		});
	}

}
