package com.negotium.Database.Connection;

import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseConnectionSingletonTest {

    private final DatabaseConnectionSingleton cut = DatabaseConnectionSingleton.getInstance();

    @Test
    public void dataSource() throws SQLException {

        DataSource dataSource = cut.dataSource();
        //assertEquals(new HikariDataSource(null),dataSource);
    }
}