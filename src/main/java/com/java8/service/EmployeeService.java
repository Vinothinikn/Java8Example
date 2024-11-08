 	package com.java8.service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java8.dto.Employee;
import com.java8.setvalues.EmployeeSetter;

@Service
public class EmployeeService {

	
	public static void employeeExample() {
		System.out.println("============================Employee Output===================================================");
		List<Employee> empList = Employee.getSampleEmployees();
		
		//Remove Duplicates
		List<Employee> removeDupList =  empList.stream().distinct().collect(Collectors.toList());
		for(Employee e : removeDupList) {
			System.out.println(e.toString());
		}
		
		//reverse the list based on the name
		List<Employee> reverseList = empList.stream()
									.sorted(Comparator.comparing(Employee::getName).reversed())
									.collect(Collectors.toList());
		System.out.println(reverseList);
		
		//min Salary and max Salary
		Optional<Employee> minSalaryEmployee = empList.stream().min(Comparator.comparing(Employee::getSalary));
		System.out.println("minSalary Employee: "+minSalaryEmployee.get());
		Optional<Employee> maxSalaryEmployee = empList.stream().max(Comparator.comparing(Employee::getSalary));
		System.out.println("maxSalary Employee: "+maxSalaryEmployee.get());
		
		//Male and female employee count
		Map<String, Long> empGenderMap= empList.stream().distinct().collect(Collectors.groupingBy(x->x.getGender(), Collectors.counting()));
		empGenderMap.entrySet().forEach(x-> System.out.println("Gender: "+x.getKey() +" Count: "+x.getValue()));
		
		//print All departments
		List<String> employeeDepts = empList.stream().map(x->x.getDepartment().getName()).distinct().collect(Collectors.toList());
		System.out.println("Department list: "+employeeDepts);
		
		//average salary of male and female
		Map<String, Double> averageSalMap= empList.stream()
										.collect(Collectors.groupingBy(x->x.getGender(), Collectors.averagingDouble(x->x.getSalary())));
		averageSalMap.entrySet().forEach(x->System.out.println("Average Salary of male and Female: "+x.getKey() + "Average: "+x.getValue()));
		
		//highest paid employee in the organization
		Optional<Employee> emp= empList.stream().max(Comparator.comparing(Employee::getSalary));
		System.out.println("Highest Salary Employee: "+emp.get().toString());
		
		//other method
		Optional<Employee> em= empList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
	
		//get all employee whose salary is greater than 100000
		List<Employee> salaryFilterEmp= empList.stream().filter(x->x.getSalary()>10000).collect(Collectors.toList());
		System.out.println("Salary greater than 1000000: "+salaryFilterEmp.toString());
		
		//Count the no of employees in each department
		Map<String, Long> empDeptCount= empList.stream().distinct().collect(Collectors.groupingBy(x->x.getDepartment().getName(), Collectors.counting()));
		empDeptCount.entrySet().forEach(x->System.out.println("No of Employees in each department: "+x.getKey() + " "+x.getValue()));
		
		//Get the details of youngest male employee in the product development department?
		Optional<Employee> employee_young= empList.stream().filter(x->x.getGender().equals("Male") && x.getDepartment().equals("IT")).min(Comparator.comparing(Employee::getAge));
		System.out.println("Youngest Employee in the department IT: " +employee_young.get());
		
		//Who has the most working experience in the organization?
		Optional<Employee> most_Employee= empList.stream().max(Comparator.comparing(Employee::getWorkExperience));
		System.out.println("Most working Experience: "+most_Employee.get());
		
		//List down the names of all employees in each department?
		Map<String, List<String>> emp_DepartmentMap= empList.stream()
													.collect(Collectors.groupingBy(x->x.getDepartment().getName(), 
															Collectors.mapping(Employee::getName, Collectors.toList())));		
		emp_DepartmentMap.entrySet().forEach(x-> System.out.println("name of all employees in each department: "+x.getKey() + " "+x.getValue()));
	
		
		//list the employee with sorted name with each department
				Map<String, List<Employee>> sortedEmployeeDept = empList.stream()
						.collect(Collectors.groupingBy(x->x.getDepartment().getName(), 
						Collectors.collectingAndThen(Collectors.toList(),
						x->x.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList()))));
				
		sortedEmployeeDept.entrySet().forEach(x-> System.out.println("Sorted Name Employee in each department: "+x.getKey() +" "+x.getValue()));
		
		//highest employee salary in each department
		Map<String, Optional<Employee>> high_Emp_Dept= empList.stream().collect(Collectors.groupingBy(x->x.getDepartment().getName(), 
				Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
		high_Emp_Dept.entrySet().forEach(x-> System.out.println("Department: "+x.getKey() +"Employee: "+x.getValue().get()));
		
		//What is the average salary and total salary of the whole organization?
		DoubleSummaryStatistics stats = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("average Salary: "+stats.getAverage());
		System.out.println("Total Salary: "+stats.getSum());
		
		// Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
		Map<Boolean, List<Employee>> partitionAge= empList.stream().collect(Collectors.partitioningBy(x->x.getAge()>25));
		partitionAge.entrySet().forEach(x-> {
			if(x.getKey()) {
				System.out.println("Employee who are older than 25 yrs: "+x.getValue());
			}else {
				System.out.println("Employee who are less than or equal 25 yrs: "+x.getValue());
			}
		});
		
		//Employee - department with IT and sort it by name
		List<Employee> sortedEmpDept= empList.stream()
									  .filter(x->x.getDepartment().equals("IT"))
									  .sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
		System.out.println("Department with It and Sort it by name: "+sortedEmpDept.toString());
		
		//list the name sorted with the department - IT
		List<String> nameSortedDept= empList.stream()
									.filter(x->x.getDepartment().getName() .equals("IT"))
									.sorted(Comparator.comparing(Employee::getName))
									.collect(Collectors.mapping(Employee::getName, Collectors.toList()));
		System.out.println("Sorted name with dept IT "+nameSortedDept.toString());
		
		//department with max no of employees
		Map<String, Long> deptCountMap = empList.stream().collect(Collectors.groupingBy(x->x.getDepartment().getName(), Collectors.counting()));
		Optional<Entry<String, Long>> deptMax= deptCountMap.entrySet().stream().max(Entry.comparingByValue());
		System.out.println("Max Employee in dept : "+deptMax.get().getKey() +" "+deptMax.get().getValue());
		
		//max salary in each department
		Map<String, Double> highSalaryDeptMap = empList.stream()
			    .distinct()
			    .collect(Collectors.groupingBy(
			       x->x.getDepartment().getName(),
			        Collectors.collectingAndThen(
			            Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
			            employee -> employee.isPresent() ? employee.get().getSalary() : 0.0 // Extract salary or use 0.0 if none found
			        )
			    ));
		highSalaryDeptMap.entrySet().forEach(x->System.out.println("Highest Salary in each dept: "+x.getKey()+" "+x.getValue()));
		
		
		
	}
}
