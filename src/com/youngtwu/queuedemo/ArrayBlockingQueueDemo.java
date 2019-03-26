package com.youngtwu.queuedemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
	public static void main(String[] args) {
		ArrayBlockingQueue<String> arrays = new ArrayBlockingQueue<String>(3);
		arrays.add("李四");
		arrays.add("张军");
		arrays.add("张军");
		// 添加阻塞队列
		try {
			arrays.offer("张三", 1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(arrays.size());
	}
}
