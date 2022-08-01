package pool;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lxw
 * @date 2022/7/28 17:28
 * 自己写的线程池
 */


public class ThreadPool {
    @Test
    public void test() {

    }
    //任务队列
    private BlockingQueue taskQueue;

    //线程集合
    private Set<Worker> works = new HashSet();

    //核心线程数
    private int coreSize;

    //任务的超时时间
    private long timeout;

    private TimeUnit unit;

    public ThreadPool(int coreSize, long timeout, TimeUnit unit, int queueCapcity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        taskQueue = new BlockingQueue(queueCapcity);
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //当task不为空
            while (task != null || (task = (Runnable) taskQueue.take()) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (works) {
                works.remove(this);
            }


        }
    }

    //执行任务
    public void execute(Runnable task) {
        synchronized (this) {
            //当任务没有超过核心线程数的话，直接交给work执行
            if (works.size() < coreSize) {
                Worker worker = new Worker(task);
                System.out.println("新建线程worker" + worker);
                worker.start();
                works.add(worker);
            }
            //当超过核心线程数的话，就加入任务队列等待着
            else if (works.size() >= coreSize) {
                System.out.println("当前线程都在忙！！！放入任务队列等待");
                taskQueue.put(task);
            }
        }
    }
}
