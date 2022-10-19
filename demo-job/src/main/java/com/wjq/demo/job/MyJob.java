package com.wjq.demo.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author wjq
 * @since 2022-02-08
 */
public class MyJob implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {

        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s,当前参数: %s," +
                        "当前任务名称: %s,当前任务参数: %s,"+
                        "当前任务的id: %s"
                ,
                //获取当前线程的id
                Thread.currentThread().getId(),
                //获取任务总片数
                shardingContext.getShardingTotalCount(),
                //获取当前分片项
                shardingContext.getShardingItem(),
                //获取当前的参数
                shardingContext.getShardingParameter(),
                //获取当前的任务名称
                shardingContext.getJobName(),
                //获取当前任务参数
                shardingContext.getJobParameter(),
                //获取任务的id
                shardingContext.getTaskId()
        ));
    }
}
