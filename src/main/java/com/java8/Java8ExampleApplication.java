package com.java8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java8.practice.PracticeJav8;
import com.java8.service.EmployeeService;
import com.java8.service.IntegerService;
import com.java8.service.StringService;
import com.java8.service.StudentService;

@SpringBootApplication
public class Java8ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java8ExampleApplication.class, args);
//		StringService.string_java8();
//		EmployeeService.employeeExample();
//		IntegerService.integerJava8();
//		StudentService.studentServiceJava8();
//		IntegerService.java8_example2();
//		StringService.java8_String_example();
		
		PracticeJav8.practiceJavaTechie();
		
	}

}
