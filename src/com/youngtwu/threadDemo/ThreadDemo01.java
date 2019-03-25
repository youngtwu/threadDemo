package com.youngtwu.threadDemo;
/**
 * 没有控制读写分离，一边写，一边读
 * @ClassName:  ThreadDemo01   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wuyantao 
 * @date:   2019年3月25日 下午3:24:39   
 *     
 * @Copyright: 2019
 */
public class ThreadDemo01 {
	public static void main(String[] args) {
		Res res = new Res();
		IntThrad intThrad = new IntThrad(res);
		OutThread outThread = new OutThread(res);
		intThrad.start();
		outThread.start();
	}

}

class Res {
	public String userSex;
	public String userName;
}

class IntThrad extends Thread {
	private Res res;

	public IntThrad(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			synchronized (res) {
//				if (res.flag) {
//					try {
//						// 当前线程变为等待，但是可以释放锁
//						res.wait();
//					} catch (Exception e) {
//
//					}
//				}
				if (count == 0) {
					res.userName = "余胜军";
					res.userSex = "男";
				} else {
					res.userName = "小紅";
					res.userSex = "女";
				}
				count = (count + 1) % 2;
				res.flag = true;
				// 唤醒当前线程
				res.notify();
			}

		}
	}
}

class OutThread extends Thread {
	private Res res;

	public OutThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res) {
//				if (!res.flag) {
//					try {
//						res.wait();
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//				}
				System.out.println(res.userName + "--" + res.userSex);
				res.flag = false;
				res.notify();
			}
		}
	}
}