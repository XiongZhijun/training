package com.fpi.bear.patterns.factory;

import com.fpi.bear.patterns.factory.Gun.HandGun;
import com.fpi.bear.patterns.factory.Gun.SubmachineGun;

public class AmericanGunFactory implements GunFactory {

	@Override
	public SubmachineGun createSubmachineGun() {
		return null;
	}

	@Override
	public HandGun createHandGun() {
		return null;
	}

}
