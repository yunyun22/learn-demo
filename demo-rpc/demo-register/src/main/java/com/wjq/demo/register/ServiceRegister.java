package com.wjq.demo.register;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author wjq
 * @since 2022-03-24
 */
@Slf4j
public class ServiceRegister {

    private ZooKeeper zookeeper;

    private final String serverAddress;

    private final String sessionTimeout;

    private static final String DEFAULT_TIME_OUT = CommonConstant.DEFAULT_TIME_OUT;
    private static final String REGISTER = CommonConstant.REGISTER;
    private static final String SERVICE_INFO = CommonConstant.SERVICE_INFO;


    public ServiceRegister(Properties zkProperties) throws IOException {

        this.serverAddress = zkProperties.getProperty("zookeeper.serverAddress");
        this.sessionTimeout = zkProperties.getProperty("zookeeper.timeout", DEFAULT_TIME_OUT);
        connect();
        init();

    }

    private void init() {
        try {
            Stat registerNode = zookeeper.exists(REGISTER, false);
            if (registerNode == null) {
                zookeeper.create(REGISTER, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("确定失败，无法创建初始节点", e);
        }
    }


    private void connect() throws IOException {
        this.zookeeper = new ZooKeeper(serverAddress, Integer.parseInt(sessionTimeout), event -> {
        });
    }


    public void register(RegisterInfo registerInfo) {
        String serviceName = registerInfo.getServiceName();
        createServiceNode(serviceName);

        String s = JSONUtil.toJsonStr(registerInfo);
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        String path = REGISTER + "/" + serviceName + SERVICE_INFO;
        try {
            zookeeper.create(path, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException | InterruptedException e) {
            log.error("注册失败", e);
        }
    }

    public void unregister() throws InterruptedException {
        zookeeper.close();
    }


    private void createServiceNode(String serviceName) {
        String servicePath = REGISTER + "/" + serviceName;

        try {
            Stat registerNode = zookeeper.exists(servicePath, false);
            if (registerNode == null) {
                zookeeper.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("创建服务节点失败", e);
        }
    }
}
