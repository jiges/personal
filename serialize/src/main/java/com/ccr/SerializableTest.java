package com.ccr;

import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(new File("D:Temp/object.dat")));

        Employee employee = new Employee();
        employee.name = "Tom";
        employee.age = 41;
//        employee.leader = employee;
        employee.say();

        objectOutput.writeObject(employee);

        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(new File("D:Temp/object.dat")));

        Employee readEmployee = (Employee) objectInput.readObject();

        readEmployee.say();
    }
}
