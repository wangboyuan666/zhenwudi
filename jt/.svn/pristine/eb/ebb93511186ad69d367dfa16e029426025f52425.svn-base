package com.jt.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
public class SpringAsyncConfig {
    /**
     * 核心线程数
     */
    private int corePoolSize =3;
    /** 最大线程数*/
    private int maximumPoolSize = 5;
    /** 活跃时间*/
    private int  kepAliveTime = 30;

    /**
     *自定义线程池
     * 池对象应用:
     * 1.当池中线程数没有达到corePoolSize的到时候,每次请求都会创建线程,存储到池中
     * 2.当池中线程数已经达到corePoolSize的时候,并且所有的线程都在执行,新的请求会进入阻塞队列
     * 3.当池中线程数已经达到corePoolSize的时候,并且所有的线程都在执行,而且阻塞队列已满,这个时候,
     * 创建新的线程,直到线程数达到maximumPoolSize.
     * @return
     */
   // @Bean("pool")
    public ThreadPoolExecutor newThreadPoolExecutor(){
        /** 基于数组存储结构,FIFO算法实现的一个阻塞队列*/
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
        /**创建池对象*/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,maximumPoolSize,kepAliveTime,
                TimeUnit.SECONDS,workQueue,threadFactory
        );
        return threadPoolExecutor;
    }
    private ThreadFactory threadFactory = new ThreadFactory() {
        private AtomicInteger atomicInteger = new AtomicInteger(1000);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"db-async"+atomicInteger);
        }
    };
}
