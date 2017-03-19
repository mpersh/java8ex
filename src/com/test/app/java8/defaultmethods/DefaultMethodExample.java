package com.test.app.java8.defaultmethods;

public class DefaultMethodExample {

	public void show() {
		System.out.println("DefaultMethodExample:");
		A vehicle = new Car();
		vehicle.defaultMethod();
		System.out.println();
	}

}

interface A {
	default void defaultMethod() {
		System.out.println("defaultMethod from A!");
	}

	static void staticDefaultMethod() {
		System.out.println("staticDefaultMethod!");
	}
}

interface B {
	default void defaultMethod() {
		System.out.println("defaultMethod from B!");
	}
}

class Car implements A, B {
	public void defaultMethod() {
		A.super.defaultMethod();
		B.super.defaultMethod();
		A.staticDefaultMethod();
		System.out.println("I am C!");
	}
}