package com.fpi.bear.patterns;

public class Singleton2 {

	private static Singleton2 singleton;

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		if (singleton == null) {
			synchronized (Singleton2.class) { // synchronized 不要会怎样？
				if (singleton == null) { // 这句判断不要会怎样？
					singleton = new Singleton2();
				}
			}
		}
		return singleton;
	}
}
