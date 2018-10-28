package segmentfault.Question1010000016803089;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 更新操作
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser() {
        int result = jdbcTemplate.update("update test set NAME = 'ABC' where id = 1");
        return result > 0;
    }

}
