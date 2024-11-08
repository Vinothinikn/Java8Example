package com.java8.practice;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java8.dto.Employee;

@Service
public class PracticeJav8 {

	public static void practiceJavaTechie() {

		List<Employee> empList = Employee.getSampleEmployees();

		// Print employee name and Salary
		empList.forEach(x -> System.out.println(x.getId() + " : " + x.getName()));

		int sizeOfList = empList.stream().distinct().collect(Collectors.toSet()).size();
		System.out.println("Size of the list after duplicates: " + sizeOfList);

		// check whether the employeeList contains duplicates
		boolean isDuplicate = empList.stream().distinct().collect(Collectors.toSet()).size() == empList.stream()
				.collect(Collectors.toList()).size();
		System.out.println(" Not Contains Duplicates: " + isDuplicate);

		// filter the employee who works under "Mathematics"
		List<Employee> employeeWithDept = empList.stream()
				.filter(x -> x.getDepartment().getName().equalsIgnoreCase("Mathematics")).collect(Collectors.toList());
		System.out.println("Employee in Department Mathematics: " + employeeWithDept.toString() + " Size: "
				+ employeeWithDept.size());

		// Display employee corresponds to department whose salary is greater than 50000
		// and gender "female"
		Map<String, String> empDeptMap = empList.stream()
				.filter(x -> x.getSalary() > 50000 && x.getGender().equalsIgnoreCase("Female"))
				.collect(Collectors.toMap(x -> x.getName() + "-" + x.getGender(), x -> x.getDepartment().getName()));
		empDeptMap.entrySet().forEach(
				entry -> System.out.println("Employee dept Map: " + entry.getKey() + " : " + entry.getValue()));

		// get all departments
		List<String> deptList = empList.stream().map(x -> x.getDepartment().getName()).distinct()
				.collect(Collectors.toList());
		System.out.println("All Department List: " + deptList);

		// get All ProjectName
		List<String> projects = empList.stream().flatMap(x -> x.getProjects().stream()).map(y -> y.getName()).distinct()
				.collect(Collectors.toList());
		System.out.println("Get All Projects: " + projects);

		// sort the employee based on Salary
		List<Employee> sortBySalaryEmps = empList.stream().sorted(Comparator.comparing(Employee::getSalary))
				.collect(Collectors.toList());
		System.out.println("Sort the Employee by Salary: " + sortBySalaryEmps.toString());

		// sort the employee by Salary in descending order and Display employee name and
		// Salary
		Map<String, Double> empSalMap = empList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary, (x1, x2) -> x1, LinkedHashMap::new));
		empSalMap.entrySet().forEach(
				e -> System.out.println("Employee and Salary in Desc order: " + e.getKey() + " : " + e.getValue()));

		// other way for descending order
		Map<String, Double> empSalMap_Collection = empList.stream()
				.sorted(Collections.reverseOrder(Comparator.comparing(Employee::getSalary)))
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary, (x1, x2) -> x1, LinkedHashMap::new));

		// Get the min salary and max salary employee
		Optional<Employee> minSalaryEmp = empList.stream().min(Comparator.comparing(Employee::getSalary));
		minSalaryEmp.ifPresentOrElse(x -> System.out.println("Min Salary Employee: " + minSalaryEmp.get()),
				() -> System.out.println("No Data"));

		Optional<Employee> maxSalaryEmp = empList.stream().max(Comparator.comparing(Employee::getSalary));
		minSalaryEmp.ifPresentOrElse(x -> System.out.println("Max Salary Employee: " + maxSalaryEmp.get()),
				() -> System.out.println("No Data"));

		// Other way
		Optional<Employee> minByEmp = empList.stream()
				.collect(Collectors.minBy(Comparator.comparing(Employee::getSalary)));
		if (minByEmp.isPresent()) {
			System.out.println("Using minBy to find Employee: " + minByEmp.get());
		}

		Optional<Employee> maxByEmp = empList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
		if (maxByEmp.isPresent()) {
			System.out.println("Using maxBy to find Employee: " + maxByEmp.get());
		}

		// Give the employee group by gender
		Map<String, List<Employee>> genderEmpMap = empList.stream().collect(Collectors.groupingBy(x -> x.getGender()));

		// Display the name alone group by fresher
		Map<Boolean, List<String>> groupByFresherMap = empList.stream().collect(Collectors
				.groupingBy(x -> x.getIsFresher(), Collectors.mapping(x -> x.getName(), Collectors.toList())));
		groupByFresherMap.entrySet()
				.forEach(x -> System.out.println("Group by Fresher: " + x.getKey() + " : " + x.getValue()));

		// Gender and their count
		Map<String, Long> genderCountMap = empList.stream()
				.collect(Collectors.groupingBy(x -> x.getGender(), Collectors.counting()));
		genderCountMap.entrySet()
				.forEach(x -> System.out.println("Gender Count map: " + x.getKey() + ": " + x.getValue()));

		// we want to find the first employee who has a salary greater than a specific
		// amount, say $100,000 - optional -orElse
		Employee firstEmploye = empList.stream().filter(x -> x.getSalary() > 100000.0)
				.sorted(Comparator.comparing(Employee::getSalary)).findFirst().orElse(new Employee());

		// Optional exception - Optional - orElseThrow
		Employee firstEmploye1 = empList.stream().filter(x -> x.getSalary() > 100000.0)
				.sorted(Comparator.comparing(Employee::getSalary)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Employee not Found"));

		System.out.println("FirstEmp whose Salary>100000: " + firstEmploye.getName() + ": " + firstEmploye.getSalary());

		// find any employee who works remotely.
		Optional<Employee> remoteEmp = empList.stream().filter(x -> x.getWorkMode().equalsIgnoreCase("Remote"))
				.findAny();
		if (remoteEmp.isPresent()) {
			System.out.println("Remote Employee: " + remoteEmp.get());
		}

		// optional - ifPresent
		remoteEmp.ifPresent(x -> System.out.println("Remote Employee- ifPresent: " + x));

		// optional - ifPresentOrElse
		remoteEmp.ifPresentOrElse(x -> System.out.println("Remote Employee - ifPresentOrElse: " + x),
				() -> System.out.println("No Employer works remotely"));

		// all employees are fresher employees - allMatch
		Boolean isAllFresher = empList.stream().allMatch(x -> x.getIsFresher());
		System.out.println("All Employees Are Freshers: " + isAllFresher);

		// no employees have a salary below a specific threshold, such as $30,000. - noneMatch
		Boolean noEmployee = empList.stream().noneMatch(x -> x.getSalary() < 30000);
		System.out.println("no employees have a salary below a specific threshold, such as $30,000: " + noEmployee);

		// if there is any employee with more than 5 years of experience - anyMatch
		Boolean expEmployee = empList.stream().anyMatch(x -> x.getWorkExperience() > 5);
		System.out.println("Any Employee more than 5 exp:" + expEmployee);

		// To skip the first top 5 employees with salary >100000 and then list employees
		// from the 6th onward.
		List<String> salEmp = empList.stream()
				.sorted(Collections.reverseOrder(Comparator.comparing(x -> x.getSalary())))
				.filter(x -> x.getSalary() > 100000).skip(5).map(x -> x.getName() + " : " + x.getSalary())
				.collect(Collectors.toList());
		System.out.println("skip the first top 5 employees with salary >100000: " + salEmp);

		// Display top 5 salary employee
		List<Employee> top5Employees = empList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
				.limit(5).collect(Collectors.toList());
		System.out.println("Top 5 Employees: " + top5Employees.toString());

		// Display employee details in each project
		// Creating a map that groups employees by the projects they are working on
		Map<String, List<String>> employeesByProject = empList.stream()
				// For each employee in the list, create a stream of entries for each project
				// they are part of
				.flatMap(emp -> emp.getProjects().stream()
						// Create a SimpleEntry where the key is the project's name and the value is the
						// employee
						.map(project -> new AbstractMap.SimpleEntry<>(project.getName(), emp)))
				// Collect the entries into a map, grouping them by project name
				.collect(Collectors.groupingBy(m -> m.getKey(), // Group by the project's name (String)
						Collectors.mapping(m -> m.getValue().getName(), Collectors.toList())
				// Collect employees for each project name in a list
				));

		employeesByProject.entrySet().forEach(
				x -> System.out.println("Employees in each project: " + x.getKey() + ": " + x.getValue().toString()));

	}
}
