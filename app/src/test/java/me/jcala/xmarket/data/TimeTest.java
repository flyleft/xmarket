package me.jcala.xmarket.data;

import org.junit.Test;

public class TimeTest {
    @Test
    public void testTime() throws InterruptedException{
        long start=System.currentTimeMillis();
        System.out.println(start);
        Thread.sleep(10000);
        long end=System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end-start);
    }
}
