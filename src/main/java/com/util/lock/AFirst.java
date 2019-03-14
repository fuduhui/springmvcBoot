package com.util.lock;

public class AFirst implements Runnable{
    @Override
    public void run(){
        try{
            System.out.println("AFirst 开始了");
                synchronized(DeadLock.A){
                    System.out.println("AFirst 获取到了A锁了");
                    Thread.sleep(3000);
                    synchronized(DeadLock.B){
                        System.out.println("AFirst 获取到了B锁了");
                    }
                }
            System.out.println("AFirst 结束了");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}