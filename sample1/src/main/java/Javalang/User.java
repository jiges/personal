package Javalang;

/**
 * Created by ccr at 2018/3/19.
 * @author Cherokee
 */
public class User {
    private User leader;
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public User getLeader() {
        return leader;
    }

    public String getUserName() {
        return userName;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
