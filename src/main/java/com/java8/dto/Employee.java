package com.java8.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Employee {

	private long id;
	private String name;
	private double salary;
	private Department department;
	private String workMode;
	private Boolean isFresher;
	private String gender;
	private Integer age;
	private double workExperience;
	private List<Project> projects;
	private List<String> skills;
	
	public Employee() {};

	public Employee(long id, String name, double salary, Department department, String workMode, Boolean isFresher,
			String gender, Integer age, double workExperience, List<Project> projects, List<String> skills) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
		this.workMode = workMode;
		this.isFresher = isFresher;
		this.gender = gender;
		this.age = age;
		this.workExperience = workExperience;
		this.projects = projects;
		this.skills = skills;
	}


	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	
	public Department getDepartment() {
		return department;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public double getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(double workExperience) {
		this.workExperience = workExperience;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}

	public Boolean getIsFresher() {
		return isFresher;
	}

	public void setIsFresher(Boolean isFresher) {
		this.isFresher = isFresher;
	}

	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass()!=o.getClass()) return false;
		Employee e = (Employee)o;
		return id == e.id ;
//				&&
//				Double.compare(e.salary,salary) == 0 &&
//				isFresher.equals(e.isFresher) &&
//				name.equals(e.name);
	}
	
	public int hashCode() {
		return Objects.hash(id);
	}

	 public static List<Employee> getSampleEmployees() {
	        List<Employee> employees = new ArrayList<>();
	        List<Department> departments = Department.getSampleDepartments();
	        List<Project> allProjects = Project.getSampleProjects();
	        List<String> skillPool = Arrays.asList("Java", "Spring Boot", "React", "Node.js", "SQL", "MongoDB", "AWS",
	                                               "Azure", "Docker", "Kubernetes", "Python", "Machine Learning");
	        // Unique names for employees
	        String[] employeeNames = {
	            "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", 
	            "Ivy", "Jack", "Kathy", "Liam", "Mia", "Noah", "Olivia", "Paul", 
	            "Quinn", "Ryan", "Sophia", "Tina", "Uma", "Victor", "Will", "Xena", "Yara", "Zane"
	        };
	        Random random = new Random();
	        for (long i = 1; i <= 25; i++) {
	        	String name = employeeNames[(int) i];
	            double salary = 50000 + (random.nextInt(100) * 1000);
	            Department department = departments.get(random.nextInt(departments.size()));
	            String workMode = random.nextBoolean() ? "Remote" : "Onsite";
	            Boolean isFresher = random.nextBoolean();
	            String gender = random.nextBoolean() ? "Male" : "Female";
	            Integer age = 22 + random.nextInt(20); // Ages between 22 and 42
	            double workExperience = isFresher ? 0.0 : (1 + random.nextDouble() * 9); // Experience between 0 and 10 years
	            List<Project> projects = Arrays.asList(
	                allProjects.get(random.nextInt(allProjects.size())),
	                allProjects.get(random.nextInt(allProjects.size()))
	            );
	            List<String> skills = new ArrayList<>();
	            for (int j = 0; j < 3 + random.nextInt(2); j++) { // 3 to 4 skills
	                skills.add(skillPool.get(random.nextInt(skillPool.size())));
	            }

	            employees.add(new Employee(i, name, salary, department, workMode, isFresher, gender, age, workExperience, projects, skills));
	        }
	        
	        
	      
	        return employees;
	    }

	    @Override
	    public String toString() {
	        return "Employee{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", salary=" + salary +
	                ", department=" + department.getName() +
	                ", workMode='" + workMode + '\'' +
	                ", isFresher=" + isFresher +
	                ", gender='" + gender + '\'' +
	                ", age=" + age +
	                ", workExperience=" + workExperience +
	                ", projects=" + projects.stream().map(Project::getName).toList() +
	                ", skills=" + skills +
	                '}';
	    }

}