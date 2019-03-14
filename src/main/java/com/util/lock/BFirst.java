package com.util.lock;

public class BFirst implements Runnable{
    @Override
    public void run(){
        try{
            System.out.println("BFirst 开始了");
                synchronized(DeadLock.B){
                    System.out.println("BFirst 获取到了B锁了");
                    Thread.sleep(3000);
                    synchronized(DeadLock.A){
                        System.out.println("BFirst 获取到了A锁了");
                    }
                }
            System.out.println("BFirst 结束了");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}