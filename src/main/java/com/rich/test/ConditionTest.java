package com.rich.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @ClassName: ConditionTest
 * @Author: 柳杨
 * @Create: 2020-12-09 14:18
 * @Version: 1.0
 */
public class ConditionTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock(true);
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        Condition conditionD = lock.newCondition();

        PrintCharTask[] printTasks = new PrintCharTask[] {
                new PrintCharTask('A', lock, conditionA, conditionB),
                new PrintCharTask('B', lock, conditionB, conditionC),
                new PrintCharTask('C', lock, conditionC, conditionD),
                new PrintCharTask('D', lock, conditionD, conditionA)
        };

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (PrintCharTask printTask : printTasks) {
            executorService.submit(printTask);
        }
    }
}


class PrintCharTask implements Runnable {
    //打印的字母
    private char ch;
    //每个线程都会共享一把锁，所以，需要从外部传递进来。
    private Lock lock;
    //当前条件，每一个线程独享一个Condition，并持有下一个通知线程的Condition
    private Condition condition;
    //下一个条件
    private Condition nextCondition;

    public PrintCharTask(char ch, Lock lock, Condition condition, Condition nextCondition) {
        this.ch = ch;
        this.lock = lock;
        this.condition = condition;
        this.nextCondition = nextCondition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (true) {
                Thread.sleep(1000);
                System.out.println(this.ch);
                //通知下一个条件上等待的线程就绪。
                nextCondition.signal();
                //本条件上运行的线程开始wait等待。
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();;
        } finally {
            lock.unlock();
        }
    }
}