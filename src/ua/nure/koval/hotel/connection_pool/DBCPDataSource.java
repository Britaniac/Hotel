package ua.nure.koval.hotel.connection_pool;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
    
    private static BasicDataSource ds = new BasicDataSource();
     
    static {
/*    		input = ClassLoader.class.getResourceAsStream("database.properties");
		    		prop.load(input);*/
		    		String url = "jdbc:mysql://localhost:3306/hotel_db";
    				//String url = "jdbc:mysql://localhost:807/hotel_db";
		    		String user = "root";
		    		String password = "";
		            ds.setUrl(url);
		            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		            ds.setUsername(user);
		            ds.setPassword(password);
		            ds.setMinIdle(1);
		            ds.setMaxIdle(5);
		            ds.setMaxOpenPreparedStatements(100); 
    }
  
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
     
    private DBCPDataSource(){ }
}
