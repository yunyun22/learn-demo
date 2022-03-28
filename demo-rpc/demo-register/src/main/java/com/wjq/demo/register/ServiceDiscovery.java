package com.wjq.demo.register;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wjq
 * @since 2022-03-24
 */
@Slf4j
public class ServiceDiscovery {


    private final Map<String, List<Server>> services = new ConcurrentHashMap<>();

    private ZooKeeper zookeeper;

    private final String serverAddress;

    private final String sessionTimeout;

    private final Set<String> set = new CopyOnWriteArraySet<>();


    public ServiceDiscovery(Properties zkProperties) {
        this.serverAddress = zkProperties.getProperty("zookeeper.serverAddress");
        this.sessionTimeout = zkProperties.getProperty("zookeeper.timeout", CommonConstant.DEFAULT_TIME_OUT);
        try {
            this.zookeeper = connect();
        } catch (IOException e) {
            log.error("启动失败", e);
        }

    }

    public List<Server> get(String serviceName) {
        if (zookeeper == null) {
            return new ArrayList<>();
        }
        ZooKeeper.States state = zookeeper.getState();
        if (state == null || !state.isAlive()) {
            return new ArrayList<>();
        }
        boolean contains = set.contains(serviceName);
        if (contains) {
            return services.get(serviceName);
        }
        set.add(serviceName);
        String servicePath = CommonConstant.REGISTER + "/" + serviceName;
        try {
            return getServers(serviceName, servicePath);
        } catch (InterruptedException | KeeperException e) {
            log.error("获取失败", e);
        }
        return new ArrayList<>();
    }

    private List<Server> getServers(String serviceName, String servicePath) throws InterruptedException, KeeperException {
        List<String> children = zookeeper.getChildren(servicePath, watcher -> {
            try {
                getServers(serviceName, servicePath);
            } catch (InterruptedException | KeeperException e) {
                log.error("启动失败", e);
            }
        });

        List<Server> list = new ArrayList<>();
        for (String child : children) {
            String key = servicePath + "/" + child;
            byte[] data = zookeeper.getData(key, null, null);
            list.add(JSONUtil.toBean(new String(data), Server.class));
        }
        services.put(serviceName, list);
        return list;
    }


    private synchronized ZooKeeper connect() throws IOException {
        if (zookeeper != null) {
            return zookeeper;
        }
        this.zookeeper = new ZooKeeper(serverAddress, Integer.parseInt(sessionTimeout), event -> {

        });
        return zookeeper;
    }

}
