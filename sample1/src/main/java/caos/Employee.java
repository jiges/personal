package caos;

import java.util.Objects;

public class Employee {

//    private int age;

    private String name = "hello123";
//
//    public Employee(String name) {
//        this.age = 1L;
//        this.name = name;
//    }

    public void say(){
        UnsafeDemo.printAddresses("this",this);
//        UnsafeDemo.printAddresses("super",super.);
    }
}
