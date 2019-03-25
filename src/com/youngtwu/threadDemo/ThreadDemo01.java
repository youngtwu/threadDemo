package com.youngtwu.threadDemo;

import com.youngtwu.threadDemo.Res;

/**
 * 没有控制读写分离，一边写，一边读
 * 
 * @ClassName: ThreadDemo01
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: wuyantao
 * @date: 2019年3月25日 下午3:24:39
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
				if (count == 0) {
					res.userName = "余胜军";
					res.userSex = "男";
				} else {
					res.userName = "小紅";
					res.userSex = "女";
				}
				count = (count + 1) % 2;
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
				System.out.println(res.userName + "--" + res.userSex);
			}
		}
	}
}