package com.test.app.java8.concurrency;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class ConcurrencyExample {

	public void show() {
		System.out.println("ConcurrencyExample:");

		completableFuture();
		stampedLockExample();
		stampedLockOptimisticExample();
		longAdderExample();
		parallelSortingExample();
		System.out.println();
	}

	private void completableFuture() {
		CompletableFuture<Float> completableFuture1 = new CompletableFuture<Float>();
		new Thread(() -> {
			try {
				Thread.sleep(4000L);
			} catch (Exception e) {
				completableFuture1.complete(-100.0f);
			}
			/*
			 * 
			 * we can manually "complete" a CompletableFuture!!
			 * 
			 * this feature is not found with the classical Future interface
			 * 
			 */
			completableFuture1.complete(100.0f);

		}, "CompFut1-Thread").start();
		System.out.println("ok...waiting at: " + new Date());
		System.out.format("compFut value and received at: %f, %s \n", completableFuture1.join(), new Date());
	}

	private void stampedLockExample() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
			long stamp = lock.writeLock();
			try {
				sleep(1);
				map.put("foo", "bar");
			} finally {
				lock.unlockWrite(stamp);
			}
		});

		Runnable readTask = () -> {
			long stamp = lock.readLock();
			try {
				System.out.println(map.get("foo"));
				sleep(1);
			} finally {
				lock.unlockRead(stamp);
			}
		};

		executor.submit(readTask);
		executor.submit(readTask);

		stop(executor);
	}
	
	private void stampedLockOptimisticExample() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
		    long stamp = lock.tryOptimisticRead();
		    try {
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        sleep(1);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		        sleep(2);
		        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
		    } finally {
		        lock.unlock(stamp);
		    }
		});

		executor.submit(() -> {
		    long stamp = lock.writeLock();
		    try {
		        System.out.println("Write Lock acquired");
		        sleep(2);
		    } finally {
		        lock.unlock(stamp);
		        System.out.println("Write done");
		    }
		});

		stop(executor);
	}
	
	private void longAdderExample() {
		LongAdder adder = new LongAdder();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		IntStream.range(0, 1000).forEach(i -> executor.submit(adder::increment));
		stop(executor);
		System.out.println(adder.sumThenReset());
	}
	
	private void parallelSortingExample() {
		String[] names = {"Angela", "Aaron", "David", "Claire", "Bob"};
		String[] namesByIndex = {"Angela", "Aaron", "David", "Claire", "Bob"};
		System.out.println("--Sort complete array--");
		Arrays.parallelSort(names);
		Arrays.stream(names).forEach(name -> System.out.println(name));
		System.out.println("--Sort array from index 2 to 4--");
		Arrays.parallelSort(namesByIndex, 2, 4);
		Arrays.stream(namesByIndex).forEach(name -> System.out.println(name));
	}

	public static void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("termination interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}

	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}