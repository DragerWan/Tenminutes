package com.czy.tenminutes.Activity;

import android.util.Log;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NetUtil {
    static private Connection con;
    private static final String TAG = "NetUtil";
    public static void getSSH() {
        String user = "root";//SSH连接用户名
        String password = "software";//SSH连接密码
        String host = "139.199.196.195";//SSH服务器
        int lport = 33104;//本地端口（随便取）
        String rhost = "localhost";//远程MySQL服务器
        int rport = 3306;//远程MySQL服务端口
        int port = 22;//SSH访问端口
        try {
            //Log.e(TAG, "1");
            JSch jsch = new JSch();
            //Log.e(TAG, "2");
            Session session = jsch.getSession(user, host, port);
            //Log.e(TAG, "3");
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            //Log.e(TAG, "4");
            session.connect();
            Log.e("=======>", "服务器连接成功");
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);//将服务器端口和本地端口绑定，这样就能通过访问本地端口来访问服务器
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            Log.e("=======>", "服务器连失败");
            e.printStackTrace();
        }
    }

    public static boolean getConnection() {
        String user = "root";//数据库连接用户名
        String password = "software";//数据库连接密码
        String url = "jdbc:mysql://localhost:33104/tenminutes";//数据库

        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME);
            con = DriverManager.getConnection(url, user, password);
            Log.e("=====连接结果=======", "数据库连接成功");
            return true;
        } catch (ClassNotFoundException e) {
            Log.e("=====连接结果=======", "报ClassNotFoundException异常");
            con = null;
        } catch (SQLException e) {
            Log.e("=====连接结果=======", "报SQLException异常");
            con = null;
        } finally {
            return false;
        }
    }

    public static String getPassword(String name) {

        if (con == null) {
            Log.e("=====连接前判断=======", "conn == null");
            return null;
        }
        String sql = "select password from user where name = \'" + name + "\'";

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = con.createStatement();
            result = statement.executeQuery(sql);
            if (result != null && result.first()) {
                int passwordColumnIndex = result.findColumn("password");
                Log.e("======结果======", "结果");
                if (!result.isAfterLast()){
                    String password = result.getString(passwordColumnIndex);
                    Log.e("======id======", password + "\t\t");
                    return password;
                }

//				System.out.print(result.getString(idColumnIndex) + "\t\t");
//				System.out.println(result.getString(nameColumnIndex));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }

            } catch (SQLException sqle) {

            }
        }
        return null;
    }

    public static boolean execSQL(Connection conn, String sql) {
        boolean execResult = false;
        if (conn == null) {
            return execResult;
        }

        Statement statement = null;

        try {
            statement = conn.createStatement();
            if (statement != null) {
                execResult = statement.execute(sql);
            }
        } catch (SQLException e) {
            execResult = false;
        }

        return execResult;
    }
}
