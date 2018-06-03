package com.charhoo.build.simplefact;

public class CarFactory {

	public static Car getCar(String car) {
		Car c = null;
		switch (car) {
		case "people":
			c = new PeopleCar();
		case "good":
			c = new PeopleCar();
		default:
		}
		return c;
	}

}
