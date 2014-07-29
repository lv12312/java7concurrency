/** Apache License
 *                         Version 2.0, January 2004
 *                      http://www.apache.org/licenses/
 *
 *  TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 */
package org.lemon.java7concurrency.test;

import java.util.concurrent.Phaser;

/**
 * phaser模拟countdownlatch
 * 
 * @author Lemon
 *
 */
public class PhaserExample {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(2);
		new MyThread(phaser).start();
		new MyThread(phaser).start();
		long time = System.currentTimeMillis();
		System.out.println("等待中~~~~");
		phaser.awaitAdvance(phaser.getPhase());
		long time2 = System.currentTimeMillis();
		System.out.println("等待时间：" + (time2 - time));
	}
}

class MyThread extends Thread {
	private Phaser phaser;

	public MyThread(Phaser phaser) {
		super();
		this.phaser = phaser;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arrive();
	}
}
