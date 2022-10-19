package com.wjq.demo.raft;

/**
 * @author wjq
 * @since 2022-03-11
 */
public interface Service {

    /**
     * 业务处理
     * @param request 请求
     * @param respond 响应
     */
    void doService(Request request,Respond respond);
}
