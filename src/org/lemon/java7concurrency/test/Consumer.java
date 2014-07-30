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
public class Consumer implements Runnable {
	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Consumer: Cycle %d\n", cycle);
			try {
				// 如果buffer为空,一直阻塞
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Consumer:(收到)" + buffer.size());
			for (int j = 0; j < 10; j++) {
				String message = buffer.get(0);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Comsumer:" + message);
				buffer.remove(0);
			}
			cycle++;
		}
	}
}
