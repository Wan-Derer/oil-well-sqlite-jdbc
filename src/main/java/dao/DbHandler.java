package dao;

import lombok.Getter;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DbHandler {
    private static final String CONN_STR = "jdbc:sqlite:./test.db";
    private static DbHandler instance = null;
    private final Connection connection;

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CONN_STR);
    }

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null) instance = new DbHandler();
        return instance;
    }



}
