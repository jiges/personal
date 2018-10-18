package segmentfault.Question1010000016671362;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpringTransaction {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void transaction_1(){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from article");
        list.forEach(System.out::println);
    }

    public void transaction_2(){

    }

}
