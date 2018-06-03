package com.charhoo.build.prototype;

public class PrototypeShallowCopy2 implements Cloneable {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	 private PrototypeShallow prototype;
	 
	 public PrototypeShallow getPrototype() {
	  return prototype;
	 }

	 public void setPrototype(PrototypeShallow prototype) {
	  this.prototype = prototype;
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
