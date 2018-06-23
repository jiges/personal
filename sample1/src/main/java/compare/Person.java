package compare;

/**
 * Created by ccr at 2018/6/22.
 */
public class Person implements Comparable<Person>{

    private String name;

    private int age;

    private int position;

    public Person(String name,int age,int position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int compareTo(Person o) {
        return this.age > o.getAge() ? 1 : (this.age == o.getAge() ? 0 : -1);
    }
}
