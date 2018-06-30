package com.ccr;

import java.io.*;

public class Employee implements Externalizable {

    private static final long serialVersionUID = 1L;

    String name;

    int age;

    public void say(){
        System.out.println("my name is " + name + ". and I'm " + age + " years old.");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //我们自决定哪些字段该序列化哪些字段不该序列化,或者该怎样序列化
        //也可以调用默认的机制
        //可以在这里进行加密
        out.writeObject("aa" + name);
        out.writeInt(age + 1);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //我们自决定哪些字段该反序列化哪些字段不该反序列化
        name = (String) in.readObject();
        age = in.readInt() - 1;
    }
}
