package com.java8.service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java8.dto.Student;
import com.java8.setvalues.StudentSetter;

@Service
public class StudentService {

	public static void studentServiceJava8() {
		System.out.println("============================Student Output===================================================");
		List<Student> studentList = StudentSetter.getStudents();
		System.out.println("Student count before : "+studentList.size());

		//Remove Duplicates
		Set<Student> distinctStudents = studentList.stream().distinct().collect(Collectors.toSet());
		System.out.println("Removed duplicates: "+distinctStudents.toString());
		System.out.println("Student count after: "+distinctStudents.size());

		//Sort Students by Percentage in Ascending Order
		List<Student> sortedStudent_Asc= studentList.stream().distinct()
				.sorted(Comparator.comparingDouble(Student::getPercentage))
				.collect(Collectors.toList());
		System.out.println("Sort_Asc Based on Percentage: "+sortedStudent_Asc.toString());

		//Sort Students by Percentage in Descending Order
		List<Student> sort_desc_Students= studentList.stream().distinct()
				.sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
				.collect(Collectors.toList());

		System.out.println("Sort_Desc Based on Percentage: "+sort_desc_Students.toString());

		// sort and extract percentage alone in a list
		List<Double> percentages_Asc = studentList.stream().distinct()
				.sorted(Comparator.comparing(Student::getPercentage))
				.map(s->Math.round(s.getPercentage()*100.0)/100.0).collect(Collectors.toList());
		System.out.println("Sort the students based on percentage and list the percentage: "+percentages_Asc);

		//Count Male and Female Students
		Map<String, Long> genderCountMap= studentList.stream().distinct()
				.collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));

		genderCountMap.entrySet().forEach(x-> System.out.println("Gender Count : "+x.getKey()+" "+x.getValue()));

		//Find the Minimum and Maximum Percentage
		DoubleSummaryStatistics stats = studentList.stream().collect(Collectors.summarizingDouble(Student::getPercentage));
		System.out.println("Min percentage: "+ Math.round(stats.getMin()*100.0)/100.0 +" Max Percentage: "+ Math.round(stats.getMax() *100.0)/100.0);

		//Find min and max age students
		Optional<Student> maxAgeStudent= studentList.stream().max(Comparator.comparing(Student::getAge));
		Optional<Student> minAgeStudent = studentList.stream().min(Comparator.comparing(Student::getAge));

		//Optional to display the values 
		//type 1 - using isPresent
		if(minAgeStudent.isPresent()) {
			System.out.println("Min Age Student: "+minAgeStudent.get());
		}else {
			System.out.println("Min Age Student: null");
		}

		//type 2 - ifPresent
		maxAgeStudent.ifPresent(x->System.out.println("Max Age Student: "+maxAgeStudent.get()));

		//type 2 - ifPresentOrElse
		maxAgeStudent.ifPresentOrElse(xl-> System.out.println("Max Age Student: "+maxAgeStudent.get()), ()-> System.out.println("No Data"));

		//Total and Average Percentage of All Students
		DoubleSummaryStatistics totalAvgStats= studentList.stream().distinct()
				.collect(Collectors.summarizingDouble(Student::getPercentage));
		System.out.println("Stats -Total percentage :"+totalAvgStats.getSum()+"Stats -Average Percentage: "+totalAvgStats.getAverage());

		//other way
		Double totalPercentage= studentList.stream().distinct().collect(Collectors.summingDouble(Student::getPercentage));
		Double averagePercentage= studentList.stream().distinct().collect(Collectors.averagingDouble(Student::getPercentage));
		System.out.println("Total Percentage using summing: "+totalPercentage+" Average Percentage using averaging: "+averagePercentage);

		//List All Distinct Departments
		Set<String> departments =  studentList.stream().distinct().map(Student::getDepartment).collect(Collectors.toSet());
		System.out.println("Distinct Departments : "+departments);

		//Average Percentage by Education Level (Undergrad or Grad)
		Map<String, Double> averagePercent_Edu= studentList.stream().distinct()
				.collect(Collectors.groupingBy(Student::getEducationLevel, 
						Collectors.averagingDouble(Student::getPercentage)));
		averagePercent_Edu.entrySet().forEach(x-> System.out.println("Average Percentage by Education level: "+x.getKey()+" "+x.getValue()));

		//Filter Students Based on Graduation Status
		List<Student> gradStudents = studentList.stream().distinct().filter(Student::getIsGraduated).collect(Collectors.toList());
		System.out.println("Graduated Students: "+gradStudents.toString());

		//Find Youngest Student in Each Department
		Map<String, Optional<Student>> minAgeStudentInDept=studentList.stream().distinct()
				.collect(Collectors.groupingBy(Student::getDepartment, 
						Collectors.minBy(Comparator.comparingInt(Student::getAge))));
		minAgeStudentInDept.entrySet().forEach(x->System.out.println("Youngest Student in Each Dept: "+x.getKey()+" "+x.getValue().get().toString()));

		//Group Students by Semester
		Map<String, List<Student>> groupBySemMap= studentList.stream().distinct()
				.collect(Collectors.groupingBy(Student::getSemester));
		groupBySemMap.entrySet().forEach(x-> System.out.println("Group Students by Semester: "+x.getKey()+ " "+x.getValue().toString()));

		//Count Students by Department
		Map<String , Long> countDeptMap= studentList.stream().distinct().collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
		countDeptMap.entrySet().forEach(x-> System.out.println("Count Students by Department: "+x.getKey()+" "+x.getValue()));

		//Top Student in Each Department
		Map<String, Optional<Student>> topStudentMap= studentList.stream()
				.collect(Collectors.groupingBy(Student::getDepartment, 
						Collectors.maxBy(Comparator.comparingDouble(Student::getPercentage))));
		topStudentMap.entrySet().forEach(x-> System.out.println("Top student in Each Department: "+x.getKey()+" "+x.getValue().get()));

		//Separate Students Above and Below 70%
		Map<Boolean, List<Student>> studentSeparateMap= studentList.stream()
				.collect(Collectors.partitioningBy(x->x.getPercentage()>70));
		studentSeparateMap.entrySet().forEach(x->{
			if(x.getKey()) {
				System.out.println("Student with above 70%:"+x.getValue().toString());
			}else {
				System.out.println("Student with below 70%:"+x.getValue().toString());
			}
		});

		//Using Teeing 
		Map<Boolean, List<Student>> teeingMap= studentList.stream().collect(
				Collectors.teeing(Collectors.filtering(student->student.getPercentage()>=70, Collectors.toList()),
						Collectors.filtering(student->student.getPercentage()<70, Collectors.toList()),
						(above70, below70)->Map.of(true, above70, false,below70)));

		teeingMap.entrySet().forEach(x->{
			if(x.getKey()) {
				System.out.println("Teeing - Student with above 70%:"+x.getValue().toString());
			}else {
				System.out.println("Teeing - Student with below 70%:"+x.getValue().toString());
			}
		});


		//Group Student Names by Year
		Map<Integer, List<String>> nameByYearMap = studentList.stream().distinct()
				.collect(Collectors.groupingBy(Student::getYear, 
						Collectors.mapping(Student::getName, Collectors.toList())));
		nameByYearMap.entrySet().forEach(x-> System.out.println("Group StudentNames by Year: "+x.getKey()+" "+x.getValue()));

		//Sort Students by Name within Each Department
		Map<String, List<String>> NameSortByDeptMap= studentList.stream().distinct()
				.collect(Collectors.groupingBy(Student::getDepartment,
						Collectors.collectingAndThen(Collectors.toList(), 
								student->student.stream().distinct()
								.map(Student::getName).sorted().collect(Collectors.toList()))));
		NameSortByDeptMap.entrySet().forEach(x->System.out.println("Sort Student name by each Dept: "+x.getKey()+" "+x.getValue()));

		//Get List of All Courses Taken
		Set<String> courses =studentList.stream().distinct().flatMap(x->x.getCourses().stream()).collect(Collectors.toSet());
		System.out.println("List all courses taken: "+courses);

		//Count Students Based on Graduation Status
		Map<Boolean, Long> studentGradCountMap= studentList.stream().distinct().collect(Collectors.groupingBy(Student::getIsGraduated, Collectors.counting()));
		studentGradCountMap.entrySet().forEach(x->{
			if(x.getKey()) {
				System.out.println("Student who are Graduated count: "+x.getValue());
			}else {
				System.out.println("Student who are not Graduated count: "+x.getValue());
			}
		});

		//Filter and Sort Students by Department and Percentage
		Set<Student> studentFilter= studentList.stream().distinct()
									.filter(x->x.getDepartment().equalsIgnoreCase("Computer Science") && x.getPercentage()>90)
									.collect(Collectors.collectingAndThen(Collectors.toSet(),
									x->x.stream().distinct()
									.sorted(Comparator.comparingDouble(Student::getPercentage))
									.collect(Collectors.toSet())));
		System.out.println("Filter and sort students by department and percentage: "+studentFilter);
		
		//Department with the Most Students
		Map<String, Long> deptCountMap = studentList.stream().distinct()
										.collect(Collectors.groupingBy(Student::getDepartment,Collectors.counting()));
		Optional<Entry<String, Long>> maxCountDept= deptCountMap.entrySet()
													.stream().collect(Collectors.maxBy(Map.Entry.comparingByValue()));
		System.out.println("Max no of Students in the department: "+maxCountDept.get().getKey() +" "+maxCountDept.get().getValue());
		
		//Find the maximum percentage achieved by students in each semester.
		Map<String, Optional<Student>> semStudentPercentMap= studentList.stream().distinct()
										.collect(Collectors.groupingBy(Student::getSemester, 
										Collectors.maxBy(Comparator.comparingDouble(Student::getPercentage))));
		semStudentPercentMap.entrySet().forEach(x-> System.out.println("Max percentage by student in each sem: "+x.getKey()+" "+x.getValue()));
		
		//list the grade with student name and grade based on percentage 
		//if percentage >=90 then Grade "A", percentage >= 80 and percentage<90 then Grade "B"
		//if percentage >=70 and percentage<80 then grade "c"
		// if percentage >=60 and percentage<70 then grade "D" and if percentage is <60 then Grade "E"
		
		Map<String, String> gradeMap = studentList.stream().distinct().collect(Collectors.toMap(student-> student.getId()+"_"+student.getName(), student->{
			Double percent = student.getPercentage();
			String grade = null;
			if(percent>=90) {
				grade = "A";
			}else if(percent>=80 && percent<90) {
				grade = "B";
			}else if(percent>=70 && percent<80) {
				grade = "C";
			}else if(percent>=60 && percent<70) {
				grade = "D";
			}else {
				grade = "F";
			}
			return grade;
		}));
		gradeMap.entrySet().forEach(x-> System.out.println("Grade for each Student: "+x.getKey()+" Grade: "+x.getValue()));
		
		//How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?
		Map<String, List<Student>> map= studentList.stream().distinct().collect(Collectors.groupingBy(Student::getName,
				TreeMap::new, Collectors.collectingAndThen(Collectors.toList(), 
						student->student.stream()
						.sorted(Comparator.comparingDouble(Student::getPercentage)).collect(Collectors.toList()))));
		Map<String, List<Student>> dup= map.entrySet().stream()
										.filter(entry->entry.getValue().size()>1)
										.collect(Collectors.toMap(entry->entry.getKey(), entry->entry.getValue()));
		dup.entrySet().forEach(x->System.out.println("Duplicate keys: "+x.getKey()+" "+x.getValue()));
		
		
	}
}
