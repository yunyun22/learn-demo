package com.wjq.demo.raft;

import java.io.InputStream;

/**
 * @author wjq
 * @since 2022-03-11
 */
public class RequestImpl implements Request{

    private InputStream inputStream;

    public RequestImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return this.inputStream;
    }
}
