package com.fpi.bear.patterns.factory;

public class CoffeeFactory implements GoodsFactory {

	@Override
	public Goods createGoods(String name) {
		return new Coffee();
	}

}
