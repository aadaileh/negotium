package com.negotium.Database.Factory;

import com.negotium.Database.Connection.DatabaseConnectionSingleton;
import feign.auth.BasicAuthRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * <h1>DatabaseFactoryAbstract Abstract Class</h1>
 *
 * <p>
 * Contains the necessary methods which are used in different places. It is introduced
 * in this application to increase the code-reusability and inheritance. It does demonstrate
 * the use of abstract classes
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@Component
public abstract class DatabaseFactoryAbstract {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseFactoryAbstract.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.feign.url}")
    private String feignUrl;

    @Value("${spring.basicauthentication.username}")
    private String basicAuthenticationUsername;

    @Value("${spring.basicauthentication.password}")
    private String basicAuthenticationPassword;

    @Autowired
    private DataSource dataSource;

    /**
     * Build connection to database
     *
     * @return Datasource to the database
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public DataSource getDataSource() throws SQLException {

        DataSource dataSource = null;

        DatabaseConnectionSingleton databaseConnectionSingleton = DatabaseConnectionSingleton.getInstance();
        databaseConnectionSingleton.setDbUrl(dbUrl);
        databaseConnectionSingleton.setUsername(username);
        databaseConnectionSingleton.setPassword(password);

        dataSource = databaseConnectionSingleton.dataSource();
        LOG.info("DataSource successfully initialized");
        return dataSource;
    }

    /**
     * Gets the Basic Authentication Interceptor
     * @return BasicAuthRequestInterceptor
     *
     *  @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    private BasicAuthRequestInterceptor getRequestInterceptor() {
        return new BasicAuthRequestInterceptor(basicAuthenticationUsername, basicAuthenticationPassword);
    }
}
