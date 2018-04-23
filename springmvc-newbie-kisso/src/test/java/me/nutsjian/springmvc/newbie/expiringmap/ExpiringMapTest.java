package me.nutsjian.springmvc.newbie.expiringmap;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ExpiringMapTest {

    @Test
    public void test01() throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder().variableExpiration().build();
        map.put("wizshu", "wizshu007", ExpirationPolicy.CREATED, 3, TimeUnit.SECONDS);
        while (true) {
            Thread.sleep(1000);
            System.out.println(map.get("wizshu"));
        }
    }


    @Test
    public void testRemove() {
        ExpiringMap<String, String> map = ExpiringMap.builder().variableExpiration().build();
        map.put("wizshu", "wizshu007");
        String ret1 = map.remove("wizshu");
        String ret2 = map.remove("wizshu1");
        System.out.println(ret1);
        System.out.println(ret2);
    }
}
