package Javalang;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ccr at 2018/3/19.
 * @author Cherokee
 */
public class OptionalTest {
    public static User get(){
        User u = new User("underling");
        u.setLeader(new User("Leader"));
        return u;
    }

    public static void main(String[] args) {
        Optional<String> leader = Optional.ofNullable(get())
                .map(User::getLeader)
                .map(User::getUserName)
                .map(String::toLowerCase);
        System.out.println(leader.orElse("Unknown"));

        List<Long> ids = new ArrayList<>();
        ids.stream().map(id -> id.toString());

    }

    public void print(Object o){
        System.out.println(o);
    }
}
