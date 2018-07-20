package com.charhoo.build.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PrototypeSerializeFather implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015693705631066453L;
	private String id;
	private PrototypeSerialize prototype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PrototypeSerialize getPrototype() {
		return prototype;
	}

	public void setPrototype(PrototypeSerialize prototype) {
		this.prototype = prototype;
	}

	public Object clone() {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);

			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return oi.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
