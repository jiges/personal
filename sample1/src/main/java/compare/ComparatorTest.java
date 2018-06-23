package compare;

import java.util.*;

/**
 * Created by ccr at 2018/6/22.
 */
public class ComparatorTest {

    public static void main(String[] args) {
        Person person1 = new Person("Messi",35,2);
        Person person2 = new Person("Kobe",31,4);

        List<Person> personList = new ArrayList<>(Arrays.asList(new Person[]{person1,person2,new Person("Toma",25,1),new Person("TOmb",25,0)}));

//        Collections.sort(personList);

//        personList.stream().map(Person::getName).forEach(System.out::println);

//        Comparator<Person> comparator = new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getPosition() > o2.getPosition() ? 1 : (o1.getPosition() == o2.getPosition() ? 0 : -1);
//            }
//        };
//        Collections.sort(personList,comparator);
//        personList.sort(comparator);

//        personList.stream().map(Person::getName).forEach(System.out::println);
//        if(comparator.compare(person1,person2) == 1) {
//            System.out.println(person1.getName() + "要比" + person2.getName() + "大...");
//        } else if(comparator.compare(person1,person2) == -1){
//            System.out.println(person2.getName() + "要比" + person1.getName() + "大...");
//        } else {
//            System.out.println(person2.getName() + "和" + person1.getName() + "一样大...");
//        }

//        Comparator<Person> byName = Comparator.comparing(Person::getName);
//        personList.sort(byName);
//        personList.stream().map(Person::getName).forEach(System.out::println);

//        //按照名称排序，忽略大小写
//        Comparator<Person> byNameIgnoreCase = Comparator.comparing(Person::getName,Comparator.comparing(String::toLowerCase));
//        //效果同上
//        //Comparator<Person> byNameIgnoreCase = Comparator.comparing(Person::getName,String.CASE_INSENSITIVE_ORDER);
//        personList.sort(byNameIgnoreCase);
//        personList.stream().map(Person::getName).forEach(System.out::println);

//        Comparator<Person> byNameLength = Comparator.comparing(Person::getName,Comparator.comparingInt(String::length));
//        personList.sort(byNameLength);
//        personList.stream().map(Person::getName).forEach(System.out::println);

        //基本类型的排序
//        Comparator<Person> byPosition = Comparator.comparingInt(Person::getPosition);
//        personList.sort(byPosition);
//        personList.stream().map(Person::getName).forEach(System.out::println);

        //自然（默认）排序规则
//        Comparator<Person> byNature = Comparator.naturalOrder();
//        personList.sort(byNature);
//        personList.stream().map(Person::getName).forEach(System.out::println);

//        Comparator<Person> nullFirst = Comparator.nullsFirst(Comparator.naturalOrder());
//        personList.sort(nullFirst);
//        personList.stream().map(o -> o == null? "null":o.getName()).forEach(System.out::println);


//        Comparator<Person> nullLast = Comparator.nullsLast(Comparator.naturalOrder());
//        personList.sort(nullLast);
//        personList.stream().map(o -> o == null? "null":o.getName()).forEach(System.out::println);

        //反转
//        Comparator<Person> byPositionReverse = Comparator.comparingInt(Person::getPosition).reversed();
//        personList.sort(byPositionReverse);
//        personList.stream().map(Person::getName).forEach(System.out::println);

//        Comparator<Person> byNatureReverse = Comparator.reverseOrder();
//        personList.sort(byNatureReverse);
//        personList.stream().map(Person::getName).forEach(System.out::println);

        Comparator<Person> complexComparator = Comparator.comparingInt(Person::getAge).thenComparing(Comparator.comparingInt(Person::getPosition));
//        Comparator<Person> complexComparator = Comparator.comparingInt(Person::getAge).thenComparing(Person::getName,String.CASE_INSENSITIVE_ORDER);
        personList.sort(complexComparator);
        personList.stream().map(Person::getName).forEach(System.out::println);

    }

}
