package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    private String driver;
    private String url;
    private String userName;
    private String passwd;
    @Override
    public Connection createConnection() {
        try {
            loadProperties();
            return DriverManager.getConnection(url,userName,passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadProperties(){
        try(InputStream inp = H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties");
        ){
            Properties properties = new Properties();
           properties.load(inp);
           driver = properties.getProperty("jdbc_driver");
           url = properties.getProperty("db_url");
           userName = properties.getProperty("user");
           passwd = properties.getProperty("password");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

