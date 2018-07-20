package com.charhoo.rpc.serialize;

import java.io.Serializable;

public class ObjectB implements Serializable {

    private static final long serialVersionUID = 2L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
