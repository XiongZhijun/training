package com.fpi.bear.patterns.factory;

public class SimpleGoodsFactory {

	public static Goods createGoods(String name) {
		if ("cigarette".equals(name)) {
			return new Cigarette();
		} else if ("coffee".equals(name)) {
			return new Coffee();
		}
		return null;
	}

}
