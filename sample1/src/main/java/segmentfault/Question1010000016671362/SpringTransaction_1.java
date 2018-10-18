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
public class SpringTransaction_1 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SpringTransaction_2 springTransaction_2;

    /**
     * 默认传播属性，当前没有事物就新建一个事物，如果有事物则加入当前事物
     *
     * 注意：transaction_1和transaction_2不要放在一个类里面，否则transaction_2
     * 就不会被AOP代理，不会创建新事物
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public void transaction_1(){
        System.out.println("transaction_1 begin");
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from test");
        System.out.println("initial view in transaction_1");
        list.forEach(System.out::println);
        springTransaction_2.transaction_2();
        list = jdbcTemplate.queryForList("select * from test");
        System.out.println("view in transaction_1 after transaction_2");
        list.forEach(System.out::println);
        System.out.println("transaction_1 end");
    }

}
