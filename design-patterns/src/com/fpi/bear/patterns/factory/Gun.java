package com.fpi.bear.patterns.factory;

public interface Gun {

	void fire();

	public static interface SubmachineGun extends Gun {
	}

	public static interface HandGun extends Gun {
	}
}
