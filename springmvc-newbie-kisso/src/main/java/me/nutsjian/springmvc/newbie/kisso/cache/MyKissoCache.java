package me.nutsjian.springmvc.newbie.kisso.cache;

import com.baomidou.kisso.SSOCache;
import com.baomidou.kisso.security.token.SSOToken;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;


/**
 *  Kisso 通过 SSOToken.getToken() 来生成 JwtToken，在生成的过程中 time 是通过 Claims.getIssure处理的，最终处理成 秒数，所以我们的 ssotoken 缓存的对象中的 time 也应该是秒数
 *
 *  这里自己实现了一个 缓存，如果要踢人必须实现 SSOCache，否则实现不了踢人的功能。
 *
 */
public class MyKissoCache implements SSOCache {

    private static final ExpiringMap<String, SSOToken> map = ExpiringMap.builder().variableExpiration().build();

    @Override
    public SSOToken get(String key, int expires) {
        SSOToken token = map.get(key);
        if (null != token) {
            if (expires == -1) {
                map.setExpiration(key, Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } else {
                map.setExpiration(key, expires, TimeUnit.MILLISECONDS);
            }
        }
        return map.get(key);
    }

    @Override
    public boolean set(String key, SSOToken ssoToken, int expires) {
        ssoToken.setTime((ssoToken.getTime() / 1000) * 1000);
        if (expires == -1) {
            map.put(key, ssoToken, ExpirationPolicy.CREATED, Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        }else {
            map.put(key, ssoToken, ExpirationPolicy.CREATED, expires, TimeUnit.MILLISECONDS);
        }
        return true;
    }

    @Override
    public boolean delete(String key) {
        map.remove(key);
        return true;
    }
}
