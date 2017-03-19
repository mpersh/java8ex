package com.test.app.java8.functionalinterfaces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalInterfaceExample {
	
	public void show() {
		System.out.println("FunctionalInterfaceExample:");
		predicatesExample();
		biConsumerInterfaceExample();
		supplierExample();
		operatorsExample();
		System.out.println();
	}
	
	private void predicatesExample() {
		List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");
		 
		List<String> namesWithA = names.stream()
		  .filter(name -> name.startsWith("A"))
		  .collect(Collectors.toList());
		names.forEach(name -> System.out.println("Hello, " + name));
		namesWithA.forEach(name -> System.out.println(name + " is starts with 'A'"));
	}
	
	private void biConsumerInterfaceExample() {
		Map<String, Integer> ages = new HashMap<>();
		ages.put("John", 25);
		ages.put("Angela", 24);
		ages.put("Samuel", 30);
		 
		ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
	}
	
	private void supplierExample() {
		int[] fibs = {0, 1};
		Stream<Integer> fibonacci = Stream.generate(() -> {
		    int result = fibs[1];
		    int fib3 = fibs[0] + fibs[1];
		    fibs[0] = fibs[1];
		    fibs[1] = fib3;
		    return result;
		});
		fibonacci.limit(10).forEach(i->System.out.println(i));
	}
	
	private void operatorsExample() {
		List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);		 
		int sum = values.stream().reduce(0, (i1, i2) -> i1 + i2);
		System.out.println(sum);
	}

}
