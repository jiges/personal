package segmentfault.Question1010000016803089;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsertService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入操作
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean insertLog() {
        int result = jdbcTemplate.update("insert into test2 (name) values ('AA')");
        return result > 0;
    }
}
