package com.java8.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class StringService {

	public static void string_java8() {
		System.out.println("============================String Output===================================================");
		//String reverse
		String data= " Hello Programming world    ! % hello ^^^ ";
		String removedSpace = data.trim().replaceAll("\\s+", " ");
		System.out.println(removedSpace);
		
		String removedSpecial = removedSpace.trim().replaceAll("[^a-zA-Z0-9\\s]", "");
		System.out.println(removedSpecial);
		String s = removedSpecial;
		
		char[] c = new char[removedSpecial.length()];
		int length = removedSpecial.length();  
		for(int i=length-1;i>=0;i--) {
			c[length-1-i] = removedSpecial.charAt(i);
		}
		String newString = new String(c);
		System.out.println(newString);
		
		//using java8
		String result = IntStream.rangeClosed(0, s.length()-1)
						.mapToObj(i-> s.charAt(s.length()-1-i))
						.map(String::valueOf).collect(Collectors.joining());
		System.out.println(result);
		
		String s2 = "Java Programming language";
		
		//Convert String to character
		List<Character> charList =  s2.chars().filter(ch->ch!=' ').mapToObj(ch ->(char)ch).collect(Collectors.toList());
		System.out.println(charList.toString().replaceAll("[\\]\\[ ,]", "").toCharArray());
		
		char[] charArray = s2.replaceAll("\\s+", "") .toCharArray();
		System.out.println(Arrays.toString(charArray));
		
		//Join the String with , operator and add delimiter [ ]
		 String sentence = "Hello how are you and welcome to java programming";
		  String delimiterString = Arrays.stream(sentence.split(" ")).collect(Collectors.joining("," , "[", "]"));
		  System.out.println("delimiterString: "+delimiterString);
		  
		  //find the count for each word in a sentence
		 Map<String, Long> wordCount=  Arrays.stream(sentence.split(" "))
				 					   .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		 wordCount.entrySet().forEach(entry-> System.out.println(entry.getKey() + entry.getValue())); 
		 
		//Separate odd numbers and even numbers;
		List<Integer> list = Arrays.asList(23,45,66,78,99,64,23,11,23,21,45,54);
	    Map<Boolean, List<Integer>> output= list.stream().distinct().collect(Collectors.partitioningBy(x->x%2==0));
		
	    output.entrySet().stream().forEach(x-> {
	    	if(x.getKey()) {
	    		System.out.println("even "+x.getValue());
	    	}else {
	    		System.out.println("Odd "+x.getValue());
	    	}
	    });
	    
	    //Remove duplicate elements from the list
	    Set<String> repeatedList = new HashSet<>();
	    List<String> stringList = Arrays.asList("Orange", "Apple", "Grape", "grape", "Pome", "Mango", "mango");
	    List<String> removedDup = stringList.stream().map(x->x.toLowerCase()).filter(x->!repeatedList.add(x)).collect(Collectors.toList());
	    System.out.println(removedDup);
	    
	    //print duplicates
	    System.out.println(repeatedList);
	    
	    //Frequency of each character in a String
	    String s3 = "Hello Java programming";
	    Map<Character, Long> charMap = s3.chars()
	    								.filter(ch-> ch!=' ')
	    								.mapToObj(ch->(char) ch)
	    								.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	    charMap.entrySet().stream().forEach(x-> System.out.println(x.getKey() +" "+x.getValue()));
	    
	    //frequency of each element in a array
	    int[] a = {3,4,6,3,6,7,5,6};
	    
	   Map<Integer, Long> intMap = Arrays.stream(a).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	   System.out.println(intMap);
	   
	   //Sort the List in the reverse order
	  List<String> sortedList= stringList.stream().map(x->x.toLowerCase()).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	  System.out.println(sortedList);
	  
	  //Find the maximum length count string 
	  Optional<String> maxLenString= stringList.stream().max(Comparator.comparingInt(String::length));
	  System.out.println(maxLenString.get());
	  
	  //sort the String based on the length
	  List<String> sortStringList = stringList.stream()
			  .map(x->x.toLowerCase()).distinct().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
	  System.out.println(sortStringList);
	  
	  //Convert List to array
	  String[] stringArray = stringList.stream().toArray(String[]::new);
	  System.out.println(stringArray[1]);
	  
	 //find the string that starts with Number
	  List<String> stringList1= Arrays.asList("Hello", "Vino123", "123Vino", "gala3lanm");
	  String numString = "Vino 23hello 345 Shorter2434";
	  
	  List<String> numStrings= stringList1.stream().filter(x->Character.isDigit(x.charAt(0))).collect(Collectors.toList());
	  System.out.println("Num String :" +numStrings);
	  
	  String[] numStringWords=  Arrays.stream(numString.split(" "))
			  .filter(x->Character.isDigit(x.charAt(0)) && x.chars().anyMatch((Character::isLetter))).toArray(String[]::new);
	 System.out.println("Num String words :" + Arrays.toString(numStringWords));
	
	 //Palindrome
	 String name = "Madam";
	 String n1 = name.toLowerCase();
	 boolean palindrom = IntStream.rangeClosed(0, n1.length()/2).allMatch(x-> n1.charAt(x)==n1.charAt(n1.length()-1-x));
	  System.out.println("Is palindrome : "+palindrom);
	  
	  //Reverse the list
	  Collections.reverse(stringList1);
	  
	  //most Repeated elements in the list
	  List<String> fruitRepeatedList = Arrays.asList("Apple", "Grapes", "Mango", "mango","Orange", "Plum", "Pome", "peaches","peaches","apple", "apple");
	  Map<String, Long> fruitMap= fruitRepeatedList.stream().map(x->x.toLowerCase())
			  .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	  Entry<String, Long> fruitRepeat= fruitMap.entrySet().stream().max(Map.Entry.comparingByValue()).get();
	  System.out.println("Most Repeated Fruit: "+fruitRepeat.getKey()+" Count : "+fruitRepeat.getValue());
	  
	  //remove duplicates from an array of strings while preserving the original order using Java 8
	  List<String> removedDupFruits= fruitRepeatedList.stream()
			  						.map(x->x.toLowerCase()).collect(Collectors.toCollection(LinkedHashSet::new))
			  						.stream().collect(Collectors.toList());
	  System.out.println("Remove duplicates and preserve the order: "+removedDupFruits.toString());
	  
	  //print the duplicate elements from the list
	  Set<String> uniqueElements = new HashSet<>();
	  Set<String> DuplicatesElements = fruitRepeatedList.stream().map(x->x.toLowerCase()).filter(x->!uniqueElements.add(x)).collect(Collectors.toSet());
	  System.out.println(" Duplicates: "+DuplicatesElements);
	  System.out.println("UniqueElements: "+uniqueElements);
	  
	  //Find first repeated character in a string?
	  String wordSentence = "Java Programming words";
	  String replaceSpace = wordSentence.replaceAll("\\s+", "");
	  System.out.println(replaceSpace);
	 Map<Character,Long> charMap1=  replaceSpace.chars().mapToObj(cr->(char)cr)
			 .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
	 Character ch= charMap1.entrySet().stream().filter(entry->entry.getValue()>1).map(entry->entry.getKey()).findFirst().get();
	 System.out.println("first Repeated character: "+ch);
	 
	 //Find first non-repeated character in a string?
	Map<Character, Long> nonRepCharMap= wordSentence.chars().filter(chr->chr!=' ')
	 .mapToObj(chr->(char)chr).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));
	
	List<Character> nonRepCharList= nonRepCharMap.entrySet().stream()
			.filter(entry->entry.getValue()==1).map(entry->entry.getKey()).collect(Collectors.toList());
	System.out.println("Non Repeated Character list : "+nonRepCharList);
	
	
	//Find the first and last element in the array
	String[] array3 = {"Book", "Pen", "Scale", "eraser","Pencil","Sketch","Crayon","chalk", "Paper", "Pad","Note"};
	Optional<String> firstElement= Arrays.stream(array3).findFirst();
	System.out.println("First element in the list: "+firstElement.get());
	
	String lastElement = Arrays.stream(array3).skip(array3.length-1).findFirst().get();
	System.out.println("Last element in the list: "+lastElement);
	}
	
	public static void java8_String_example() {
		System.out.println("============================java8_example - String Output===================================================");
		//Given a String, find the first non-repeated character in it using Stream functions?
		String data = "Java Data Programming Language Java";
		Map<Character, Long> charaterCount= data.chars().filter(ch->ch!=' ')
											.mapToObj(ch->(char)ch).collect(Collectors.groupingBy(x->x, 
											LinkedHashMap::new, Collectors.counting()));
		Optional<Character> firstNonRepeatChar= charaterCount.entrySet().stream()
												.filter(entry->entry.getValue()==1)
												.map(entry->entry.getKey()).findFirst();
		firstNonRepeatChar.ifPresent(x->System.out.println("First Non Repeated character: "+firstNonRepeatChar.get()));
		
		//Given a String, find the first repeated character in it using Stream functions?
		Optional<Character> firstRepeatedChar= data.chars().filter(ch->ch!=' ')
											  .mapToObj(ch->(char)ch).collect(Collectors.groupingBy(x->x, 
											   LinkedHashMap::new, Collectors.counting()))
											   .entrySet().stream().filter(entry->entry.getValue()>1)
											   .map(entry->entry.getKey()).findFirst();
		firstRepeatedChar.ifPresent(x->System.out.println("First Repeated character: "+firstRepeatedChar.get()));
		
		//How to use map to convert object into Uppercase in Java 8?
		String toUpperCase = data.chars().mapToObj(ch->(char)ch)
							.map(Character::toUpperCase).map(String::valueOf)
							.collect(Collectors.joining());
		System.out.println("To upper case: "+toUpperCase);
		
		//How to count each element/word from the String ArrayList in Java8?
		long co= data.chars().filter(c->c!=' ').mapToObj(c->(char)c).count();
		System.out.println("Count no of letters including duplicates : "+co);
		
		long count_total_words= Arrays.stream(data.split("\\s+")).filter(x->!x.isEmpty()).count();
		System.out.println("Count total no of words: "+count_total_words);
		
		//remove duplicates in String and preserves the sentences
		String sentence = "Java java programming language data data good language data";
		String newSentence= Arrays.stream(sentence.split(" "))
		.map(String::toLowerCase).collect(Collectors.toCollection(LinkedHashSet::new))
		.stream().collect(Collectors.joining(" "));
		System.out.println("remove dup and preserve the sentence: "+newSentence);
		
		
		//remove consecutive words and preserve the sentence
		String dat[] = sentence.split(" ");
		List<String> newdat= new LinkedList<>();
		String lastWord = "";
		for(String wor: dat) {
			
			if(!wor.equalsIgnoreCase(lastWord)) {
				newdat.add(wor);
			}
			lastWord = wor;
		}
		String finalOutput = newdat.stream().collect(Collectors.joining(" "));
		System.out.println("remove consecutive words and preserve the sentence: "+finalOutput);	
		
		//How to find only duplicate elements with its count from the String ArrayList in Java8
		Map<String, Long> dupMap= Arrays.stream(sentence.split(" ")).map(x->x.toLowerCase())
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
		.filter(entry->entry.getValue()>1).collect(Collectors.toMap(entry->entry.getKey(), entry->entry.getValue()));
		dupMap.entrySet().forEach(x->System.out.println("Elements: "+x.getKey()+" "+x.getValue()));
		
		//How to check if list is empty in Java 8 using Optional, if not null iterate through the list and print the object
		List<String> listData = Arrays.asList("Apple", null, "Orange", "Grape", "Grape", "dates", null);
		Optional.ofNullable(listData).orElseGet(Collections::emptyList)
		.stream().filter(Objects::nonNull).distinct().forEach(System.out::println);
		
		//find the largest word in the sentence
		Optional<String> largestWord= Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length));
		System.out.println("Largest word in a sentence: "+largestWord.get());
		//find the largest word in a list
		Optional<String> largestWordInList= listData.stream().filter(Objects::nonNull).max(Comparator.comparing(String::length));
		System.out.println("Largest word in a List: "+largestWordInList.get());
		
		//Write a program to print the count of each character in a String?
		Map<Character, Long> charCountMap= sentence.chars().filter(ch->ch!=' ')
										.mapToObj(ch->(char)ch)
										.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		charCountMap.entrySet().forEach(x->System.out.println("Character Count: "+x.getKey()+" "+x.getValue()));
		
		//remove duplicate from the char array
		char[] abc = {'a','b','b','d','a','s'};
		Set<Character> uniqueSet = new HashSet<>();
		
        char[] removeDupChar = new String(abc).chars() // Convert to IntStream
                .mapToObj(c -> (char) c) // Convert to Character objects
                .filter(x -> uniqueSet.add(x)) // Filter duplicates
                .map(String::valueOf) // Convert to String
                .collect(Collectors.joining()) // Join into a single String
                .toCharArray(); // Convert back to char array
		
    	System.out.println("RemoveDuplicate chars: "+Arrays.toString(removeDupChar));
        
    	//Remove duplicates and preserve the sentence;
		String sent = "Jaavaa Pprrooggrraammiinngg";
		Set<Character> charSet = new HashSet<>();
		String removeSent= sent.chars().mapToObj(x->(char)x)
						  .filter(x->charSet.add(x)).map(String::valueOf).collect(Collectors.joining(""));
		System.out.println("Remoce Duplicate and sent: "+removeSent);
		
		//consecutive char remove and preserve the sentence
		List<Character> seenCharList = new LinkedList<>();
		char[] charArray = sent.toCharArray();
		Character last = null;
		for(int i=0;i<charArray.length;i++) {
			if(last==null || ! last.toString().equalsIgnoreCase(String.valueOf(charArray[i]))) {
				seenCharList.add(charArray[i]);
			}
			last = charArray[i];
		}
		String conseSent= seenCharList.stream().map(String::valueOf).collect(Collectors.joining()); 
		System.out.println("Consecutive: "+conseSent);
		
		//Reverse the sentence and remove duplicates and remove extra space and special characters
		String complex = "Javaaaaa    Programmingging  +@1343;..,.";
		//String removeSpace = complex.trim().replaceAll("\\s+", " ");
		String removeSpecial = complex.replaceAll("[^A-Za-z\\s]", "");
		System.out.println("RemoveSpecial: "+removeSpecial);
		
		
		Set<Character> dupCharSet = new HashSet<>();
	   String unique=removeSpecial.chars().mapToObj(x->(char)x)
			   		 .filter(x->dupCharSet.add(x)).map(String::valueOf).collect(Collectors.joining());
	   int len = unique.length();
		
		System.out.println("unique: "+unique);
		char[] a = new char[len];
		for(int i= a.length-1;i>=0;i--) {
			a[a.length-1-i] = unique.charAt(i);
		}
		String dat_con = new String(a);
		System.out.println("Remove duplicates and reverse: "+dat_con);
	}
}
