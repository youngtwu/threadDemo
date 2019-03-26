package com.youngtwu.queuedemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueDemo {
	public static void main(String[] args) {
		LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(3);
		linkedBlockingQueue.add("张三");
		linkedBlockingQueue.add("李四");
		boolean offer = linkedBlockingQueue.add("李四");
		System.out.println(offer);
		
//		boolean offer1 = linkedBlockingQueue.add("李四");
//		System.out.println(offer1);
		
//		try {
//			boolean offer1 = linkedBlockingQueue.add("李四");
//			System.out.println(offer1);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		// 添加阻塞队列
		try {
			boolean offer2 = linkedBlockingQueue.offer("张三", 1, TimeUnit.SECONDS);
			System.out.println(offer2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(linkedBlockingQueue.size());
	}
}
