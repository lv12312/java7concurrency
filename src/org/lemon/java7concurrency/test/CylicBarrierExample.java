/** Apache License
 *                         Version 2.0, January 2004
 *                      http://www.apache.org/licenses/
 *
 *  TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 */
package org.lemon.java7concurrency.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lemon
 *
 */
public class CylicBarrierExample {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3, new RunnabelObject()); // �����߳�ͬʱ����
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�1����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���ѵ���"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�2����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���ѵ���"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�3����ǰ����"
								+ (cb.getNumberWaiting() + 1)
								+ "���ѵ���"
								+ (cb.getNumberWaiting() == 2 ? "�������ˣ������߰�"
										: "���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);

		}
		service.shutdown();
	}
}

/**
 * 
 * @author Lemon
 *
 */
class RunnabelObject implements Runnable {

	@Override
	public void run() {
		System.out.println("...ִ��Runable����");

	}

}
