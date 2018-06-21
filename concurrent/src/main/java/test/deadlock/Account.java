package test.deadlock;

import java.math.BigDecimal;

/**
 * Created by ccr at 2018/5/15.
 */
public class Account {

    BigDecimal amount = new BigDecimal(100000);


    public void debit(BigDecimal amount) {
        this.amount = amount.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.amount = amount.add(amount);
    }

}
