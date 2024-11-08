package com.java8.setvalues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.java8.dto.Student;

@Service
public class StudentSetter {

	public static List<Student> getStudents(){
		  List<Student> students = new ArrayList<>();
	        Random random = new Random();

	        // Sample data for names, genders, departments, semesters, and education levels
	        String[] names = {
	            "Alice", "Bob", "Charlie", "Diana", "Ethan", "Frank", "Grace", "Hannah",
	            "Ivan", "Judy", "Kevin", "Laura", "Mike", "Nina", "Oscar", "Paul",
	            "Quinn", "Rachel", "Alice", "Tina"
	        };
	        String[] genders = {"Male", "Female"};
	        String[] departments = {
	            "Computer Science", "Mathematics", "Physics", "Chemistry", "Biology"
	        };
	        String[] semesters = {
	            "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5"
	        };
	        String[] educationLevels = {"Undergrad", "Grad"};
	        String[][] courseOptions = {
	            {"Data Structures", "Algorithms", "Databases"},
	            {"Calculus", "Linear Algebra", "Statistics"},
	            {"Quantum Mechanics", "Electromagnetism", "Thermodynamics"},
	            {"Organic Chemistry", "Inorganic Chemistry", "Analytical Chemistry"},
	            {"Genetics", "Microbiology", "Biochemistry"},
	            {"Artificial Intelligence", "Machine Learning", "Computer Vision"},
	            {"Abstract Algebra", "Number Theory", "Differential Equations"},
	            {"Astrophysics", "Nuclear Physics", "Optics"},
	            {"Environmental Chemistry", "Physical Chemistry", "Polymer Chemistry"},
	            {"Anatomy", "Botany", "Zoology"}
	        };

	        // Generate 15 unique students
	        for (int i = 0; i < 15; i++) {
	            int id = i + 1;
	            String name = names[random.nextInt(names.length)];
	            int age = 20 + random.nextInt(5); // Age between 20 and 24
	            String gender = genders[random.nextInt(genders.length)];
	            double percentage = 50 + random.nextDouble() * 50; // Percentage between 50 and 100
	            List<String> courses = Arrays.asList(courseOptions[i % courseOptions.length]); // Unique courses per student
	            String department = departments[random.nextInt(departments.length)];
	            String semester = semesters[random.nextInt(semesters.length)];
	            int year = 2023; // Current year or any year you prefer
	            String educationLevel = educationLevels[random.nextInt(educationLevels.length)];
	            Boolean isGraduated = random.nextBoolean(); // Randomly assign graduation status

	            students.add(new Student(id, name, age, gender, percentage, courses, department, semester, year, educationLevel, isGraduated));
	        }

	        // Add 5 duplicates
	        for (int i = 0; i < 5; i++) {
	            students.add(students.get(random.nextInt(15))); // Add random existing student
	        }
	        return students;
	}
}
