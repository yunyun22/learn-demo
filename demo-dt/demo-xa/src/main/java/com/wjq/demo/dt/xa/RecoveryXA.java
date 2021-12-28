package com.wjq.demo.dt.xa;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wjq
 * @since 2021-11-22
 */
public class RecoveryXA {

    public static void main(String[] args)  throws SQLException {
        //true表示打印XA语句,，用于调试
        boolean logXaCommands = true;
        // 获得资源管理器操作接口实例 RM2
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://139.155.73.132:3306/tx-xa-02", "root", "123456");
        XAConnection xaConn2 = new MysqlXAConnection((JdbcConnection) conn2, logXaCommands);
        XAResource rm2 = xaConn2.getXAResource();
        // AP请求TM执行一个分布式事务，TM生成全局事务id
        byte[] gtrid = "g12345".getBytes();
        byte[] bqual2 = "b00002".getBytes();
        int formatId = 1;
        Xid xid2 = new MysqlXid(gtrid, bqual2, formatId);
        // 执行rm2上的事务分支
        try {
            rm2.commit(xid2,true);
        } catch (XAException e) {
            e.printStackTrace();
        }
    }
}
