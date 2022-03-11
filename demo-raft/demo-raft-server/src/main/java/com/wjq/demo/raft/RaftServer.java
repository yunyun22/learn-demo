package com.wjq.demo.raft;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author wjq
 * @since 2022-03-11
 */
public class RaftServer {
    public static void main(String[] args) throws IOException {
        Properties properties = System.getProperties();
        new RaftServer(properties).start();
    }

    public RaftServer(Properties properties) {
        this.properties = properties;
    }

    private volatile boolean over = false;
    private final Properties properties;

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(properties.getProperty("demo.raft.port")));
        while (!over) {
            //从请求队列中取出一个连接
            Socket client = serverSocket.accept();
            // 处理这次连接
            new HandlerThread(client, new ServiceImpl());
        }
    }

    public void end() {
        over = true;
    }


    private static class HandlerThread implements Runnable {
        private Socket socket;
        private Service service;

        public HandlerThread(Socket client, Service service) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                service.doService(new RequestImpl(inputStream), new RespondImpl(outputStream));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                    }
                }
            }
        }
    }
}
