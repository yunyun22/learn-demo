package com.wjq.demo.job;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.dataflow.props.DataflowJobProperties;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.script.props.ScriptJobProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

/**
 * @author wjq
 * @since 2022-02-08
 */
public class JobApplication {

    public static void main(String[] args) throws IOException {


         new ScheduleJobBootstrap(createRegistryCenter(), "SCRIPT", JobConfiguration.newBuilder("scriptElasticJob", 3)
                .cron("0/5 * * * * ?").setProperty(ScriptJobProperties.SCRIPT_KEY, buildScriptCommandLine()).build()).schedule();
//        ScheduleJobBootstrap scheduleJobBootstrap = new ScheduleJobBootstrap(createRegistryCenter(), new MyDataflowJob(), createJobConfiguration());
//        scheduleJobBootstrap.schedule();
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("139.155.73.132:2181", "my-data-flow-job1"));
        regCenter.init();
        return regCenter;
    }

    private static JobConfiguration createJobConfiguration() {

        //dataflow的执行
        return JobConfiguration.newBuilder("MyDataFlowJob", 3).setProperty(DataflowJobProperties.STREAM_PROCESS_KEY, Boolean.TRUE.toString()).cron("0/5 * * * * ?").build();

    }

    private static String buildScriptCommandLine() throws IOException {
        if (System.getProperties().getProperty("os.name").contains("Windows")) {
            return Paths.get(JobApplication.class.getResource("/script/demo.bat").getPath().substring(1)).toString();
        }
        Path result = Paths.get(JobApplication.class.getResource("/script/demo.sh").getPath());
        Files.setPosixFilePermissions(result, PosixFilePermissions.fromString("rwxr-xr-x"));
        return result.toString();
    }
}
