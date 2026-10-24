package com.example.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		// JVM en UTC, comme serverTimezone=UTC et @JsonFormat(timezone = "UTC") :
		// sinon une date lue en base peut sortir decalee d'un jour en JSON (ex. 1985-08-31 au lieu de 1985-09-01)
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
