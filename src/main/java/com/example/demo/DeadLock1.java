package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Component
public class DeadLock1 {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    public void deadLock() {
        Thread threadA = new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "获取lockA成功");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "尝试获取lockB");
                    synchronized (lockB) {
                        System.out.println(Thread.currentThread().getName() + "获取lockB成功");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "获取lockB成功");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "尝试获取lockA");
                    synchronized (lockA) {
                        System.out.println(Thread.currentThread().getName() + "获取lockA成功");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        threadA.start();
        threadB.start();
    }

}


