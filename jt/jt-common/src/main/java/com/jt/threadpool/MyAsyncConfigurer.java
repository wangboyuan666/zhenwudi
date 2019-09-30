package com.jt.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
//@Component
public class MyAsyncConfigurer implements AsyncConfigurer {

    /**
     *
     * 定义一个最大数量为10 的线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ExecutorService service = Executors.newFixedThreadPool(
                        10,new MyNamedThreadFactory("你过来呀"));
        return service;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    class MyAsyncExceptionHandler
            implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(
                Throwable ex, Method method, Object... params) {
            log.info("Exception message = "+ex.getMessage());
            log.info("MethodName -"+method.getName());
            for (Object param : params){
                log.info("Parameter value -"+param);
            }
        }
    }
}
