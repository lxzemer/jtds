package ovv.manage.jtds.runner;
import java.util.concurrent.*;

public class ovThreadPool{

    public static int corePoolSize = 1;
    public static int maximumPoolSize = 5;
    public static long keepAliveTime = 3000;
    public static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(5);
    public static RejectedExecutionHandler reject = new ThreadPoolExecutor.AbortPolicy();

    public static ExecutorService pool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            TimeUnit.MILLISECONDS,
            workQueue,
            Executors.defaultThreadFactory(),
            reject
    );
}