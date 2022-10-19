package com.wjq.demo.raft;

import java.io.InputStream;

/**
 * @author wjq
 * @since 2022-03-11
 */
public interface Request {

    /**
     * 获取输入流
     *
     * @return 输入流
     */
    InputStream getInputStream();
}
