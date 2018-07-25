package com.sc.wll;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Database {
    private static DataSource dataSource = null;

    static {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl("jdbc:mysql://10.18.43.12/news_wll?characterEncoding=utf8&useSSL=false");
        ds.setUser("root");
        ds.setPassword("root");
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            System.err.println("数据库驱动加载失败");
            System.exit(1);
        }
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("");
//        }
        //
        ds.setAcquireIncrement(5);
        ds.setInitialPoolSize(10);
        ds.setMinPoolSize(10);
        ds.setMaxPoolSize(50);
        Database.dataSource = ds;

    }

    private Connection getConnection() {
        try {
            return Database.dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("获取数据库连接失败");
            System.exit(2);
            return null;
        }
    }

    public int insertArticle(Article article) throws SQLException {
        String sql = "insert into news (title,url,publish_time,insert_time,text)"
                + "  select ?,?,?,?,? from dual where not exists (" +
                "select * from news where url=?" +
                ")";
        Connection connection = this.getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, article.getTitle());
        pstm.setString(2, article.getUrl());
        pstm.setTimestamp(3,new java.sql.Timestamp(article.getPublishTime().getTime()));
        pstm.setTimestamp(4,new java.sql.Timestamp(new Date().getTime()));
        pstm.setString(5, article.getContent());
        pstm.setString(6, article.getUrl());
        int rowcount = pstm.executeUpdate();
        pstm.close();
        connection.close();
        return rowcount;
    }
}
