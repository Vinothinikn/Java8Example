package com.java8.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

		private Long id;
	    private String name;
	    private String description;
	    private Date startDate;
	    private Date endDate;
	    private double budget;
	    
	    public Project() {};
	    
		public Project(Long id, String name, String description, Date startDate, Date endDate, double budget) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.startDate = startDate;
			this.endDate = endDate;
			this.budget = budget;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public double getBudget() {
			return budget;
		}
		public void setBudget(double budget) {
			this.budget = budget;
		}
	    
		 public static List<Project> getSampleProjects() {
		        List<Project> projects = new ArrayList<>();
		        projects.add(new Project(1L, "AI Research Project", "Developing advanced AI models", new Date(1234567890000L), new Date(1266103890000L), 500000.00));
		        projects.add(new Project(2L, "Mobile App Development", "Building a cross-platform mobile application", new Date(1285677090000L), new Date(1307315490000L), 300000.00));
		        projects.add(new Project(3L, "Website Redesign", "Revamping the corporate website", new Date(1328131890000L), new Date(1359667890000L), 100000.00));
		        projects.add(new Project(4L, "Cloud Migration", "Migrating services to cloud infrastructure", new Date(1379806290000L), new Date(1401342290000L), 750000.00));
		        projects.add(new Project(5L, "Cybersecurity Enhancement", "Improving network security measures", new Date(1422960690000L), new Date(1454496690000L), 600000.00));
		        projects.add(new Project(6L, "Data Analytics Platform", "Building a data analytics dashboard", new Date(1476125490000L), new Date(1497763890000L), 400000.00));
		        projects.add(new Project(7L, "CRM System Upgrade", "Upgrading the Customer Relationship Management system", new Date(1518384690000L), new Date(1550023090000L), 450000.00));
		        projects.add(new Project(8L, "E-commerce Platform", "Developing an online shopping portal", new Date(1570066290000L), new Date(1601602290000L), 550000.00));
		        projects.add(new Project(9L, "IoT Smart Home Integration", "Integrating IoT devices for smart home automation", new Date(1622506290000L), new Date(1654042290000L), 650000.00));
		        projects.add(new Project(10L, "Blockchain Pilot", "Implementing a blockchain proof of concept", new Date(1675162290000L), new Date(1706698290000L), 800000.00));
		        return projects;
		    }

		@Override
		public String toString() {
			return "Project [id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate
					+ ", endDate=" + endDate + ", budget=" + budget + "]";
		}
		 
		 
}
