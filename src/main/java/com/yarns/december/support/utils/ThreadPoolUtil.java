package com.yarns.december.support.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类（如果需要可维护性请把常量改为自定义value）
 * @author Yarns
 * @date 2018-11-2
 */
public class ThreadPoolUtil {

    /**
     * 线程缓冲队列
     */
    private static BlockingQueue<Runnable> bQueue = new ArrayBlockingQueue<>(100);
    /**
     * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 5;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 50;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final long ALIVE_TIME = 2000;

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.MILLISECONDS, bQueue, new ThreadPoolExecutor.CallerRunsPolicy());

    static {

        pool.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getPool() {
        return pool;
    }

}
