package com.charhoo.build.single;

public enum SingleEnum {

	INSTANCE;
	
	private String str = null;
	
	private SingleEnum(){
		str = "abs";
	}
	
	public int instance() {
		return str.length();
	}

}
