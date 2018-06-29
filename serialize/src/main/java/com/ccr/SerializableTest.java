package com.ccr;

import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(new File("D:Temp/object.dat")));
//
//        Employee employee = new Employee("Tom");
//
//        objectOutput.writeObject(employee);

        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(new File("D:Temp/object.dat")));

        Object readEmployee = objectInput.readObject();

        System.out.println("Object read from file is " + readEmployee);
    }
}
