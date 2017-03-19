package com.test.app.java8.methodreferences;

import java.util.ArrayList;
import java.util.List;

public class MethodReferenceExample {
	
	public void show() {
		System.out.println("MethodReferenceExample:");
		List<String> cars = new ArrayList<String>();

		cars.add("Audi");
		cars.add("Toyota");
		cars.add("Ford");
		cars.add("BMW");
		cars.add("Renault");

		cars.forEach(System.out::println);
		System.out.println();
	}

}
