package com.negotium.Database.Connection;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseConnectionSingletonTest {

    DatabaseConnectionSingleton cut = DatabaseConnectionSingleton.getInstance();

    @Before
    public void setUp() throws Exception {

//        Field instance = DatabaseConnectionSingleton.class.getDeclaredField("instance");
//        instance.setAccessible(true);
//        instance.set(null, null);
    }

    @Test
    public void dataSource() throws SQLException {

        DataSource dataSource = cut.dataSource();
        //assertEquals(new HikariDataSource(null),dataSource);
    }
}