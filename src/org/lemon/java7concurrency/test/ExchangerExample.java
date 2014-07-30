/** Apache License
 *                         Version 2.0, January 2004
 *                      http://www.apache.org/licenses/
 *
 *  TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 */
package org.lemon.java7concurrency.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author Lemon
 *
 */
public class ExchangerExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();

		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);

		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);

		threadConsumer.start();
		threadProducer.start();

	}

}
