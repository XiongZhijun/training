package com.fpi.bear.patterns.factory;

public class CigaretteFactory implements GoodsFactory {

	@Override
	public Goods createGoods(String name) {
		return new Cigarette();
	}

}
