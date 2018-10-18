package segmentfault.Question1010000016671362;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SpringTransaction_2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 如果用@Transactional 默认REQUIRED传播机制，和Transaction1在同一个事物中，
     * 由于REPEATABLE_READ隔离级别,它的更新会影响到transaction_1方法
     *
     * 这里使用REQUIRES_NEW无论何时都新建一个事物，由于REPEATABLE_READ隔离级别,
     * 此方法的更新，在transaction_1中不被察觉
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ)
    public void transaction_2(){
        System.out.println("transaction_2 begin");
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from test");
        System.out.println("initial view in transaction_2");
        list.forEach(System.out::println);
        System.out.println("update record in transaction_2");
        System.out.println("update test set name = 'AAAS' where id = 1");
        jdbcTemplate.update("update test set name = 'AAAS' where id = 1");
        System.out.println("view in transaction_2 after update");
        list = jdbcTemplate.queryForList("select * from test");
        list.forEach(System.out::println);
        System.out.println("transaction_2 end");
    }
}
