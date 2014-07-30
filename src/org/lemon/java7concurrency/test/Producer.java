/** Apache License
 *                         Version 2.0, January 2004
 *                      http://www.apache.org/licenses/
 *
 *  TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 */
package org.lemon.java7concurrency.test;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author Lemon
 *
 */
public class Producer implements Runnable {
	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Producer: Cycle %d\n", cycle);
			for (int j = 0; j < 10; j++) {
				String message = "Event " + ((i * 10) + j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}
			try {
				// 如果Exchanger数据没有被接收，阻塞。
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Producer:(剩余)" + buffer.size());
			cycle++;
		}

	}
}
