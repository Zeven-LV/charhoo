package com.charhoo.build.prototype;

import java.io.Serializable;

public class PrototypeSerialize implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4928828284604186344L;
	private String name;

	public PrototypeSerialize() {
	}

	public PrototypeSerialize(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
