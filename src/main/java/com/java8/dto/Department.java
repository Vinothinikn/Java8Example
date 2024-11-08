package com.java8.dto;

import java.util.ArrayList;
import java.util.List;

public class Department {

		private Long id;
	    private String name;
	    private String location;
	    
	    public Department() {}
	    
		public Department(Long id, String name, String location) {
			super();
			this.id = id;
			this.name = name;
			this.location = location;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
	    
		 public static List<Department> getSampleDepartments() {
		        List<Department> departments = new ArrayList<>();
		        departments.add(new Department(1L, "IT", "Building A"));
		        departments.add(new Department(2L, "Mathematics", "Building B"));
		        departments.add(new Department(3L, "Physics", "Building C"));
		        departments.add(new Department(4L, "Chemistry", "Building D"));
		        departments.add(new Department(5L, "Biology", "Building E"));
		        departments.add(new Department(6L, "Mechanical Engineering", "Building F"));
		        departments.add(new Department(7L, "Electrical Engineering", "Building G"));
		        departments.add(new Department(8L, "Civil Engineering", "Building H"));
		        departments.add(new Department(9L, "Business Administration", "Building I"));
		        departments.add(new Department(10L, "Psychology", "Building J"));
		        return departments;
		    }
}
