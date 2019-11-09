package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoLockTests {
    @Autowired
    private DeadLock1 deadLock;
    private DeadLock2 deadLock2;

    @Test
    //死锁
    void contextLoads() {
        deadLock.deadLock();
        try{
            Thread.currentThread().join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //不死锁
    @Test
    void contextLoads1(){
        deadLock2.deadLock();
        try{
            Thread.currentThread().join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
