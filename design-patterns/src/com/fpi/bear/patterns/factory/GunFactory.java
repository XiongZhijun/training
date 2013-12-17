package com.fpi.bear.patterns.factory;

import com.fpi.bear.patterns.factory.Gun.HandGun;
import com.fpi.bear.patterns.factory.Gun.SubmachineGun;

public interface GunFactory {

	SubmachineGun createSubmachineGun();

	HandGun createHandGun();
}
