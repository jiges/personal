package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {


    public static void main(String[] args)
    {
        // create a ScheduledExecutorService with a Thread Pool of 7 threads
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(7);
        log("go");
        // starts a timer of 30 seconds, shutting down ses afterwards
        ses.schedule(() -> call(ses), 30, TimeUnit.SECONDS);
        // starts the ticker
        ses.schedule(() -> tick(ses, 1), 1, TimeUnit.SECONDS);
    }

    // ticks once per second, logging the current tick counter. (i.e, counts
// by 1 each second) ticking ends when ses is shutdown.
    public static void tick(ScheduledExecutorService ses, int count)
    {
        if (!ses.isShutdown())
        {
            log("tick %d", count);
            ses.schedule(() -> tick(ses, count + 1), 1, TimeUnit.SECONDS);
        }
    }

    // called when it's time to shutdown ses.
    public static void call(ScheduledExecutorService ses)
    {
        log("done");
        ses.shutdown();
    }

    // formats and logs the given message alongside a timestamp and the name
// of the executing thread
    public static void log(String s, Object...args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        String thread = Thread.currentThread().getName();
        String message = String.format(s, args);
        String log = String.format("%s [%s] - %s", time, thread, message);
        System.out.println(log);
    }
}
