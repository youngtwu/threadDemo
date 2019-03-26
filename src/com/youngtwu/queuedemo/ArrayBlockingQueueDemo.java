package com.youngtwu.queuedemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * 有边界阻塞队列，超出范围添加失败
 * @ClassName:  ArrayBlockingQueueDemo   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wuyantao 
 * @date:   2019年3月26日 下午2:31:35   
 *     
 * @Copyright: 2019
 */
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
