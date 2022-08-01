package pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lxw
 * @date 2022/7/28 17:03
 * 阻塞队列
 */
public class BlockingQueue<T> {
    //双端队列
    private Deque<T> deque = new ArrayDeque<>();
    //锁
    private ReentrantLock lock = new ReentrantLock();
    //消费者的条件变量
    private Condition emptyWaitSet = lock.newCondition();
    //生产者的条件变量
    private Condition fullWaitSet = lock.newCondition();

    //容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            //将时间统一转化为毫秒
            long nanos = unit.toNanos(timeout);

            while (deque.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T element) {
        lock.lock();
        try {
            while (deque.size() == capcity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            deque.add(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //获取队列大小
    public int size() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }
}
