package com.youngtwu.threadDemo;

/**
 * 控制读写分离，写完再读，读完再写
 * 
 * @ClassName: ThreadDemo02
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: wuyantao
 * @date: 2019年3月25日 下午3:24:04
 * 
 * @Copyright: 2019
 */
public class ThreadDemo02 {
	public static void main(String[] args) {
		Res02 res = new Res02();
		IntThrad02 intThrad = new IntThrad02(res);
		OutThread02 outThread = new OutThread02(res);
		intThrad.start();
		outThread.start();
	}

}

class Res02 {
	public String userSex;
	public String userName;
	// 线程通讯标识
	public boolean flag = false;
}

class IntThrad02 extends Thread {
	private Res02 res;

	public IntThrad02(Res02 res) {
		this.res = res;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			synchronized (res) {
				if (res.flag) {// res.flag=true，等待
					try {
						// 当前线程变为等待，但是可以释放锁
						res.wait();
					} catch (Exception e) {

					}
				}
				System.out.println("开始写。。。。。。。");
				if (count == 0) {
					res.userName = "余胜军";
					res.userSex = "男";
				} else {
					res.userName = "小紅";
					res.userSex = "女";
				}
				count = (count + 1) % 2;
				res.flag = true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("写结束。。。。。。。");
				System.out.println("唤醒读线程。。。。。。。");
				// 唤醒当前线程
				res.notify();
			}

		}
	}
}

class OutThread02 extends Thread {
	private Res02 res;

	public OutThread02(Res02 res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res) {
				if (!res.flag) {// res.flag=false，等待
					try {
						res.wait();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				System.out.println("开始读。。。。。。。");
				System.out.println(res.userName + "--" + res.userSex);
				res.flag = false;
				System.out.println("读结束。。。。。。。");
				System.out.println("唤醒写线程。。。。。。。。");
				res.notify();
			}
		}
	}
}