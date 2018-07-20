package com.charhoo.construct.bridge;

public abstract class Bridge {

	private Place place;
	
	public void getPlaceName(){
		place.getPlaceName();
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	
	
}
