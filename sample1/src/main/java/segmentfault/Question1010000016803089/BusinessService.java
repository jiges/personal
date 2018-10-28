package segmentfault.Question1010000016803089;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusinessService {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private InsertService insertService;

    /**
     * 先插入在更新
     */
    @Transactional(rollbackFor = Exception.class)
    public void addScore1() throws InterruptedException {
        boolean flag = insertService.insertLog();
        if(flag) {
            System.out.println(String.format("%s:新增日志成功",Thread.currentThread().getName()));
            Thread.sleep(System.currentTimeMillis()%10*1000);
            updateService.updateUser();
            System.out.println(String.format("%s:更新用户成功",Thread.currentThread().getName()));
        }
    }

    /**
     * 先更新再插入
     */
    @Transactional(rollbackFor = Exception.class)
    public void addScore2() throws InterruptedException {

        boolean flag = updateService.updateUser();
        if(flag) {
            System.out.println(String.format("%s:更新用户成功",Thread.currentThread().getName()));
            Thread.sleep(System.currentTimeMillis()%10*1000);
            insertService.insertLog();
            System.out.println(String.format("%s:新增日志成功",Thread.currentThread().getName()));
        }
    }

}
