package com.test.app.java8.lambdas;

public class LambdasExample {

	public void show() {
		System.out.println("LambdasExample:");

		// with type declaration
		MathOperation addition = (int a, int b) -> a + b;

		// with out type declaration
		MathOperation subtraction = (a, b) -> a - b;

		// with return statement along with curly braces
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// without return statement and without curly braces
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("100 + 5 = " + operate(100, 5, addition));
		System.out.println("100 - 5 = " + operate(100, 5, subtraction));
		System.out.println("100 x 5 = " + operate(100, 5, multiplication));
		System.out.println("100 / 5 = " + operate(100, 5, division));

		// without parenthesis
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// with parenthesis
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		greetService1.sayMessage("Ivan");
		greetService2.sayMessage("Jack");
		System.out.println();
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}

}
