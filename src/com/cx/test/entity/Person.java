package com.cx.test.entity;

import java.io.Serializable;

/**
 * Created by cxspace on 16-8-23.
 */

public class Person implements Serializable{

    private String id;

    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
