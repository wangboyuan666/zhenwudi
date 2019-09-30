package com.jt.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyNamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    MyNamedThreadFactory(String name ){
        SecurityManager securityManager = System.getSecurityManager();
        group = (securityManager != null)?securityManager.getThreadGroup():
                Thread.currentThread().getThreadGroup();
        if (null == name || name.isEmpty()){
            name = "pool";
        }
    namePrefix = name +"-"+poolNumber.getAndIncrement()+"-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group,r,namePrefix+threadNumber.getAndIncrement(),0);
        if (thread.isDaemon())
            thread.setDaemon(false);
        if (thread.getPriority() != Thread.NORM_PRIORITY)
            thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}
