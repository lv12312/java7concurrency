package org.lemon.java7concurrency.test;

import java.util.concurrent.Semaphore;

/**
 * 
 * @author Lemon
 *
 */
public class SemaphoreExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < thread.length; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread-" + i);
		}

		for (int i = 0; i < thread.length; i++) {
			thread[i].start();
		}

	}

}

class PrintQueue {
	private final Semaphore semaphore;

	public PrintQueue() {
		semaphore = new Semaphore(1);
	}

	/**
	 * 
	 * @param document
	 */
	public void printJob(Object document) {
		try {
			semaphore.acquire();
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s PrintQueue a Job during %d seconds\n", Thread
					.currentThread().getName(), duration);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}

class Job implements Runnable {
	PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a job.\n ", Thread
				.currentThread().getName());
		// print job
		printQueue.printJob(new Object());
		System.out
				.printf("%s: Completed.\n ", Thread.currentThread().getName());
	}

}
