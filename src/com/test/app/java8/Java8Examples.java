package com.test.app.java8;

import com.test.app.java8.concurrency.ConcurrencyExample;
import com.test.app.java8.defaultmethods.DefaultMethodExample;
import com.test.app.java8.functionalinterfaces.FunctionalInterfaceExample;
import com.test.app.java8.lambdas.LambdasExample;
import com.test.app.java8.methodreferences.MethodReferenceExample;
import com.test.app.java8.streams.StreamsExample;

public class Java8Examples {
	
	public static void main(String[] args) {
		LambdasExample lambdasExample = new LambdasExample();
		lambdasExample.show();
		
		MethodReferenceExample methodReferenceExample = new MethodReferenceExample();
		methodReferenceExample.show();
		
		FunctionalInterfaceExample functionalInterfaceExample = new FunctionalInterfaceExample();
		functionalInterfaceExample.show();
		
		DefaultMethodExample defaultMethodExample = new DefaultMethodExample();
		defaultMethodExample.show();
		
		StreamsExample streamsExample = new StreamsExample();
		streamsExample.show();
		
		ConcurrencyExample concurrencyExample = new ConcurrencyExample();
		concurrencyExample.show();
	}

}
