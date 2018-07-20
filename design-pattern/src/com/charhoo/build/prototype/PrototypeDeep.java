package com.charhoo.build.prototype;

public class PrototypeDeep implements Cloneable{

	private String name;

	public PrototypeDeep() {
	}

	public PrototypeDeep(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
