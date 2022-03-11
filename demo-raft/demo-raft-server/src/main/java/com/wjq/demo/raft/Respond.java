package com.wjq.demo.raft;

import java.io.OutputStream;

/**
 * @author wjq
 * @since 2022-03-11
 */
public interface Respond {


    /**
     * 获取输出流
     *
     * @return 输出流
     */
    OutputStream getOutputStream();
}
