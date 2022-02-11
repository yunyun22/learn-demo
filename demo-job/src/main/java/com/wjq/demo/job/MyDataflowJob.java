package com.wjq.demo.job;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;

import java.util.List;

/**
 * @author wjq
 * @since 2022-02-08
 */
public class MyDataflowJob implements DataflowJob<Integer> {


    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {

        System.out.println("执行线程id:" + Thread.currentThread().getId() + ",任务名称：" + shardingContext.getJobName()+",分片："+shardingContext.getShardingItem());
        return Lists.newArrayList(1, 2, 3);
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> list) {
        System.out.println("fetch data:" + list);
    }
}
