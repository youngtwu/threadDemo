package com.youngtwu.threadLocalDemo;

/**
 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
 * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本
 * 
 * @ClassName: ThreadDemo02
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: wuyantao
 * @date: 2019年3月25日 下午3:24:04
 * 
 * @Copyright: 2019
 */
class Res {
	// 生成序列号共享变量
	public static Integer count = 0;
	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};

	public Integer getNum() {
		int count = threadLocal.get() + 1;
		threadLocal.set(count);
		return count;
	}
}

public class ThreadLocaDemo extends Thread {
	private Res res;

	public ThreadLocaDemo(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + res.getNum());
		}
	}

	public static void main(String[] args) {
		Res res = new Res();
		ThreadLocaDemo threadLocaDemo1 = new ThreadLocaDemo(res);
		ThreadLocaDemo threadLocaDemo2 = new ThreadLocaDemo(res);
		ThreadLocaDemo threadLocaDemo3 = new ThreadLocaDemo(res);
		threadLocaDemo1.start();
		threadLocaDemo2.start();
		threadLocaDemo3.start();
	}

}
