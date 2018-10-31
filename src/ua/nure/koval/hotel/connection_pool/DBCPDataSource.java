package ua.nure.koval.hotel.connection_pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
    
    private static BasicDataSource ds = new BasicDataSource();
     
    static {
    	Properties prop = new Properties();
    	InputStream input = null;
    	try {
    		input = new FileInputStream("database.properties");
    		prop.load(input);
    		String url = prop.getProperty("url");
    		String user = prop.getProperty("dbuser");
    		String password = prop.getProperty("dbpass");
            ds.setUrl(url);
            ds.setUsername(user);
            ds.setPassword(password);
            ds.setMinIdle(1);
            ds.setMaxIdle(5);
            ds.setMaxOpenPreparedStatements(100);
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} 
    }
  
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
     
    private DBCPDataSource(){ }
}
