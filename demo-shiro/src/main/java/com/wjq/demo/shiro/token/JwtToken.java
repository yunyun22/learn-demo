package com.wjq.demo.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wjq
 * @since 2021-10-12
 */
public class JwtToken implements AuthenticationToken {

    private String credentials;

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
