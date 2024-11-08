package com.java8.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service	
public class IntegerService {

	public static void integerJava8() {
		System.out.println("============================Integer Output===================================================");
		List<Integer> intList = Arrays.asList(1,45,32,56,76,77,88,65,44,1,45, null);

		Set<Integer> duplicatElements = new HashSet<>();
		//find the remove duplicates
		List<Integer> removeDuplicates = intList.stream()
										.filter(x->x!=null && !duplicatElements.add(x)).collect(Collectors.toList());
		System.out.println("duplicate Elements "+duplicatElements);
		System.out.println("RemovedDuplicate "+removeDuplicates);

		//sort the numbers in ascending and descending
		List<Integer> ascList = intList.stream().filter(x->x!=null).distinct().sorted().collect(Collectors.toList());
		System.out.println("Ascending List "+ascList);

		List<Integer> descList = intList.stream().filter(x->x!=null).distinct()
								.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println("Descending List "+descList);

		// to find min element
		int[] intArray = {1,35,67,77,54,67,33, 5,6,23,44,11,400,234,54654,455,33};
		Optional<Integer> minValue = Arrays.stream(intArray).boxed().min(Comparator.comparing(Integer::intValue));
		minValue.ifPresent(x-> System.out.println("Min Element: "+x));

		//to Find max
		Optional<Integer> maxElement= Arrays.stream(intArray).boxed().max(Comparator.comparing(Integer::intValue));
		System.out.println("Max Element: " +maxElement.orElse(0));

		//print multiples of 5
		List<Integer> mulOf5 = Arrays.stream(intArray).boxed().filter(x->x%5==0).collect(Collectors.toList());
		System.out.println("Multiples of 5 "+mulOf5);

		//merge 2 unsorted Array into single sorted Array
		int[] array1 = {56,42,22,67,87};
		int[] array2 = {2,5,8,4,43,89};
		
		int[] sortedArray = IntStream.concat(Arrays.stream(array1), Arrays.stream(array2)).sorted().toArray();
		System.out.println(Arrays.toString(sortedArray));
		
		//Anagram program in java 8
		IntSummaryStatistics sumOfArray1= Arrays.stream(array1).boxed().collect(Collectors.summarizingInt(Integer::intValue));
		 System.out.println("Sum of Arrays: "+sumOfArray1.getSum());
		 
		 // first Three min and max number in a list
		int[] max3Element= Arrays.stream(intArray).boxed().sorted(Comparator.comparing(Integer::intValue).reversed()).limit(3).mapToInt(x->x).toArray();
		System.out.println( "Max first 3 element: " +Arrays.toString(max3Element));
	
		int[] min3Element= Arrays.stream(intArray).boxed().sorted(Comparator.comparing(Integer::intValue)).limit(3).mapToInt(x->x).toArray();
		System.out.println( "Min first 3 element: " +Arrays.toString(min3Element));
	
		//Reverse the Integer Array
		int[] reverseInt= IntStream.rangeClosed(0, array1.length-1).map(i->array1[array1.length-1-i]).toArray();
		System.out.println("Reverse the IntArray: "+Arrays.toString(reverseInt));
		
		//Age of person in years
		LocalDate birthDay = LocalDate.of(1994, 10, 8);
		LocalDate today = LocalDate.now();
		System.out.println("AGE :"+ChronoUnit.YEARS.between(birthDay, today));
		
		//Fibonacci series
		int a = 0, b = 1;
		int arr[] = new int[5];
		for(int i = 0;i<arr.length;i++) {
			int next = a+b;
			a = b;
			b = next;
			arr[i] = next;
		}
		System.out.println("Fibonacci : "+Arrays.toString(arr));
		
		List<Integer> fibNum= Stream.iterate(new int[] {0, 1}, fib-> new int[]{fib[1], fib[0]+fib[1]}).limit(10).map(fib->fib[0]).collect(Collectors.toList());
		System.out.println("Fibonacci :" +fibNum);
		
		//Odd numbers
		IntStream.range(0, 20).filter(x->x%2!=0).forEach(x->System.out.println(x));
		
		//odd numbers using iterate
		List<Integer> oddNum=  IntStream.iterate(1, n->n+2).limit(10).boxed().collect(Collectors.toList());
		System.out.println("Odd numbers: "+oddNum);
		
		//even Numbers
		List<Integer> evenNum = IntStream.iterate(2, n->n+2).limit(10).boxed().collect(Collectors.toList());
		System.out.println("Even numbers: "+evenNum);
		
		// isPrime
		int num = 7;
		System.out.println(" To check Prime: "+num+" ??" + isPrime(num));
		
		//List the prime from 1 to 10;
		List<Integer> primeNumbers= IntStream.iterate(1, n->n+1).limit(10)
				.filter(IntegerService::isPrime).boxed().collect(Collectors.toList());
		System.out.println("Prime Numbers: "+primeNumbers);
		
	}
	
	public static Boolean isPrime(int number) {
		return number>1 && IntStream.range(2, (int) Math.sqrt(number)).noneMatch(n->number%n==0);
	}
	
	public static void java8_example2() {
		System.out.println("============================Integer Output - java8_example2===================================================");
		
		//Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
		List<Integer> intList = Arrays.asList(23,56,66,43,32,22,11,67,8676,765,789754,5565,657,563,546,5657, 23,56,66,43);
		List<Integer> evenList = intList.stream().distinct().filter(x->x%2==0).collect(Collectors.toList());
		System.out.println("Even Numbers: "+evenList);
		
		//Given a list of integers, find out all the numbers starting with 1 using Stream functions?
		List<String> startsWith1= intList.stream().map(x->x.toString())
								  .filter(x->x.startsWith("1")).collect(Collectors.toList());
		System.out.println("Starts with 1: "+startsWith1);
		
		//How to find duplicate elements in a given integers list in java using Stream functions?
		Set<Integer> uniqueElements = new HashSet<>();
		List<Integer> duplicateElements =  intList.stream().filter(x->!uniqueElements.add(x)).collect(Collectors.toList());
		System.out.println("Unique Elements: "+uniqueElements + " Duplicate Elements: "+duplicateElements);
		
		//Given the list of integers, find the first element of the list using Stream functions?
		 Optional<Integer> firstElement =  intList.stream().findFirst();
		 firstElement.ifPresent(x->System.out.println("First Element  :"+firstElement.get()));
		 
		 // Given a list of integers, find the total number of elements present in the list using Stream functions?
		 Long countElements= intList.stream().count();
		 System.out.println("No of Elements: "+countElements);
		 
		 int[] arr = {34,54,66,43,22,12,54,78,90,600};
		 
		 //Given a list of integers, find the maximum value element present in it using Stream functions?
		 Optional<Integer> maxElement= Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).findFirst();
		 if(maxElement.isPresent()) {
			 System.out.println("Maximum value: "+maxElement.get());
		 }
		 
		 Optional<Integer> largestElement= Arrays.stream(arr).boxed().max(Comparator.comparing(Integer::intValue));
		 int maxData= largestElement.orElseGet(()->exceptionThrow());
		 System.out.println("Largest Element: "+maxData);
		 
		 //Given a list of integers, sort all the values present in it using Stream functions?
		 List<Integer> sortedList = intList.stream().distinct().sorted().collect(Collectors.toList());
		 System.out.println("Sorting the numbers: "+sortedList);
		 
		 //Given a list of integers, sort all the values present in it in descending order using Stream functions?
		 List<Integer> sortedDescList = intList.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		 System.out.println("Sorting-Desc the numbers: "+sortedDescList);
		 
		 //Given an integer array nums, return true if any value appears at least twice in the array, 
		 //and return false if every element is distinct.
		 boolean containsDuplicates = Arrays.stream(arr).boxed().distinct().collect(Collectors.toSet()).size() ==
				 						  arr.length;
		 System.out.println("Contains Duplicates: "+containsDuplicates);
		 
		 //How will you get the current date and time using Java 8 Date and Time API?
		 LocalDate date = LocalDate.now();
		 LocalTime time = LocalTime.now();
		 System.out.println("Today Date: "+date.toString()+" Today Time: "+time.toString());
		 
		 //Write a Java 8 program to concatenate two Streams?
		 Stream<Integer> stream1 = Stream.of(1,4,5,6);
		 Stream<Integer> stream2 = Stream.of(4,7,8,9);
		 List<Integer> concatStream =Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
		 System.out.println("Concatenate 2 streams: "+concatStream);
		 
		 //Java 8 program to perform cube on list elements and filter numbers greater than 50.
		 List<Integer> cubeList= IntStream.range(1, 10).boxed()
				 				.map(i->i*i*i).filter(x->x>50).collect(Collectors.toList());
		 System.out.println("Cube of numbers: "+cubeList);
		 
		 //Write a Java 8 program to sort an array and then convert the sorted array into Stream?
		 Arrays.parallelSort(arr);
		 System.out.println("sort the array");
		 Arrays.stream(arr).forEach(x->System.out.println(x));
		 
			//Write a Program to find the Maximum element in an array?
		 int[] arr2 = {45,67,89,343,23333,54,0,3434};
		 OptionalInt maxele= Arrays.stream(arr2).max();
		 System.out.println("Max element in a array: "+maxele.getAsInt());
	}
	
	public static Integer exceptionThrow() {
		return 0;
	}
}
