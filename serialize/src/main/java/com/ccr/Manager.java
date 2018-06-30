package com.ccr;

import java.io.Serializable;

public class Manager extends Employee implements Serializable {

//    public Manager(String name) {
//        super(name);
//    }

    @Override
    public void say(){
        System.out.println("I'm son object,my name is " + name);
    }
}
