package me.nutsjian.springmvc.newbie.kisso.cache;

import com.baomidou.kisso.SSOCache;
import com.baomidou.kisso.security.token.SSOToken;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyKissoCache2 implements SSOCache {

    private static final ConcurrentMap<String, SSOToken> map = new ConcurrentHashMap<>();

    @Override
    public SSOToken get(String key, int expires) {
        SSOToken token = map.get(key);
        if (token != null) {
            token.setTime(token.getTime() + expires);
        }
        return token;
    }

    @Override
    public boolean set(String key, SSOToken ssoToken, int expires) {
        ssoToken.setTime(ssoToken.getTime() + expires);
        map.put(key, ssoToken);
        return true;
    }

    @Override
    public boolean delete(String key) {
        map.remove(key);
        return true;
    }
}
