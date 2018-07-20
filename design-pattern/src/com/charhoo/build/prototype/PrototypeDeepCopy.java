package com.charhoo.build.prototype;

public class PrototypeDeepCopy implements Cloneable {

	private String id;
	private PrototypeDeep prototype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PrototypeDeep getPrototype() {
		return prototype;
	}

	public void setPrototype(PrototypeDeep prototype) {
		this.prototype = prototype;
	}

	public Object clone() {
		PrototypeDeepCopy ret = null;
		try {
			ret = (PrototypeDeepCopy) super.clone();
			ret.prototype = (PrototypeDeep) this.prototype.clone();
			return ret;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
