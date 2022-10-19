package com.wjq.demo.raft;

import java.io.OutputStream;

/**
 * @author wjq
 * @since 2022-03-11
 */
public class RespondImpl implements Respond{

    private OutputStream outputStream;

    public RespondImpl(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outputStream;
    }
}
