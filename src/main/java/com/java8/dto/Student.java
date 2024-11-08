package com.java8.dto;

import java.util.List;
import java.util.Objects;

public class Student {

	  private int id;
	    private String name;
	    private int age;
	    private String gender;
	    private double percentage;
	    private List<String> courses;
	    private String department;
	    private String semester;
	    private int year;
	    private String educationLevel; // Undergrad or Grad
	    private Boolean isGraduated; // Indicates if the student has graduated
	    
	    public Student() {}

	    // Constructor
	    public Student(int id, String name, int age, String gender, double percentage,
	                   List<String> courses, String department, String semester, int year,
	                   String educationLevel, Boolean isGraduated) {
	        this.id = id;
	        this.name = name;
	        this.age = age;
	        this.gender = gender;
	        this.percentage = percentage;
	        this.courses = courses;
	        this.department = department;
	        this.semester = semester;
	        this.year = year;
	        this.educationLevel = educationLevel;
	        this.isGraduated = isGraduated;
	    }
	    
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public double getPercentage() {
			return percentage;
		}

		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}

		public List<String> getCourses() {
			return courses;
		}

		public void setCourses(List<String> courses) {
			this.courses = courses;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getSemester() {
			return semester;
		}

		public void setSemester(String semester) {
			this.semester = semester;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getEducationLevel() {
			return educationLevel;
		}

		public void setEducationLevel(String educationLevel) {
			this.educationLevel = educationLevel;
		}

		public Boolean getIsGraduated() {
			return isGraduated;
		}

		public void setIsGraduated(Boolean isGraduated) {
			this.isGraduated = isGraduated;
		}

		@Override
	    public String toString() {
	        return "Student{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", age=" + age +
	                ", gender='" + gender + '\'' +
	                ", percentage=" + percentage +
	                ", courses=" + courses +
	                ", department='" + department + '\'' +
	                ", semester='" + semester + '\'' +
	                ", year=" + year +
	                ", educationLevel='" + educationLevel + '\'' +
	                ", isGraduated=" + isGraduated +
	                '}';
	    }
	    
	    
	    
	    public boolean equals(Object o) {
	    	if(this== o) return true;
	    	if(!(o instanceof Student)) return false;
	    	Student s = (Student)o;
	    	return id==s.id;
	    }
	    
	    public int hashCode() {
	    	return Objects.hash(id);
	    }
}
