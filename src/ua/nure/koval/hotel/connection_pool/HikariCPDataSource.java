package ua.nure.koval.hotel.connection_pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {
     
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
     
    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_db");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(16);
        config.setConnectionTimeout(30000);
        config.setLeakDetectionThreshold(50000);
        ds = new HikariDataSource(config);
    }
     
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
     
    private HikariCPDataSource(){}
}
