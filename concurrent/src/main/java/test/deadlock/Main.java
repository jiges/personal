package test.deadlock;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by ccr at 2018/5/15.
 */
public class Main {
    public static void transferMoney(Account from, Account to, BigDecimal amount) {
        System.out.println("正在转账 from " + from + "to " + to);
        from.debit(amount);
        to.credit(amount);
    }

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[5];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }
        class TransferThread extends Thread{
            @Override
            public void run(){
                for (int i = 0; i < 1000000; i++) {
                    int fromAcct = rnd.nextInt(5);
                    int toAcct = rnd.nextInt(5);
                    Account from = accounts[fromAcct];
                    Account to = accounts[toAcct];
                    synchronized (from) {
                        synchronized (to) {
                            Main.transferMoney(from,to,new BigDecimal(100));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            new TransferThread().start();
        }
    }
}
