package stackflow.Questions52792493;

import java.io.*;

public class SerializableTest {

    static class Employee implements Serializable {

        private static final long serialVersionUID = 1L;

        String name;

        int age;

        Employee leader;

        public void say(){
            System.out.println("my name is " + name + ". and I'm " + age + " years old.");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(new File("D:Temp/object.dat")));

        Employee employee = new Employee();
        employee.name = "Tom";
        employee.age = 41;
        employee.leader = employee;
        employee.say();
        objectOutput.writeObject(employee);

        ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(new File("D:Temp/object.dat")));
        Employee readEmployee = (Employee) objectInput.readObject();

        readEmployee.say();
        readEmployee.leader.say();


        employee.name = "Jerry";
        employee.name = "21";

        objectOutput.writeObject(employee);

        Employee readEmployee1 = (Employee) objectInput.readObject();

        readEmployee1.say();
        readEmployee1.leader.say();




    }
}