package com.negotium.Services.Main.impl;

import com.negotium.DTOs.Credentials;
import com.negotium.DTOs.*;
import com.negotium.Factory.CommonFactoryAbstract;
import com.negotium.Services.Authentication.AuthenticationServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;
import java.util.UUID;

/**
 * <h1>Authentication service implementations</h1>
 *
 * <p>
 * Contains the implementation of all members of the Authentication-Service
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@Service
public class MainServiceImplentations extends CommonFactoryAbstract {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceController.class);

    @Autowired
    private DataSource dataSource;

    public String registerUser(User user) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        String token = user.getUniqid();


        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            Long credentialsId = insertIntoCredentials(user, connection, token);
            Long usersId = insertIntoUsers(user, connection, credentialsId);
            insertIntoContactInformation(user, connection, usersId);

            return token;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return null;

        } finally {
            connection.close();
        }
    }

    public Boolean confirm(String code) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            Long credentialsId = selectConfirmationCodeId(connection, code);
            if (credentialsId.equals(null)) {
                return false;
            }

            return true;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return null;

        } finally {
            connection.close();
        }
    }

    private Long insertIntoCredentials(User user, Connection connection, String token) throws SQLException {
        String credentialsStmt = "INSERT INTO `credentials` (`username`, `password`, `token`, `created_date_time`) " +
                "VALUES (?,?,?,?)";

        PreparedStatement pStmtCredentials = connection.prepareStatement(credentialsStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCredentials.setString(1, user.getEmail());
        pStmtCredentials.setString(2, user.getPassword());
        pStmtCredentials.setString(3, token);
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        pStmtCredentials.setDate(4, date);

        pStmtCredentials.executeUpdate();

        Long credentialsId;
        try (ResultSet generatedKeys = pStmtCredentials.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                credentialsId = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating credentials failed, no ID obtained.");
            }
        }

        return credentialsId;
    }

    private Long insertIntoUsers(User user, Connection connection, Long credentialsId) throws SQLException {

        String usersStmt = "INSERT INTO `users` (`type`, `name`, `surname`, `email`, `tel`, `credentials_id`, `created_date_time`) " +
                "VALUES ('job_seeker', ?, ?, ?, ?, ?, ?);";

        PreparedStatement pStmtUsers = connection.prepareStatement(usersStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtUsers.setString(1, user.getName());
        pStmtUsers.setString(2, user.getSurname());
        pStmtUsers.setString(3, user.getEmail());
        pStmtUsers.setString(4, user.getTel());
        pStmtUsers.setLong(5, credentialsId);
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        pStmtUsers.setDate(6, date);

        pStmtUsers.executeUpdate();

        Long usersId;
        try (ResultSet generatedKeys = pStmtUsers.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                usersId = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating users failed, no ID obtained.");
            }
        }

        return usersId;
    }

    private Long insertIntoContactInformation(User user, Connection connection, Long usersId) throws SQLException {

        String usersStmt = "INSERT INTO `contact_information` (`email`, `mobile`, `users_id`) " +
                "VALUES (?, ?, ?);";

        PreparedStatement pStmtContactInfo = connection.prepareStatement(usersStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtContactInfo.setString(1, user.getEmail());
        pStmtContactInfo.setString(2, user.getTel());
        pStmtContactInfo.setLong(3, usersId);

        pStmtContactInfo.executeUpdate();

        Long contactInfoId;
        try (ResultSet generatedKeys = pStmtContactInfo.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                contactInfoId = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating contact_information failed, no ID obtained.");
            }
        }

        return contactInfoId;
    }


    private Long selectConfirmationCodeId(Connection connection, String code) throws SQLException {

        ResultSet resultSet = null;
        Statement statement = null;
        Long credentialsId = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM credentials WHERE token = '" + code + "';";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                credentialsId = resultSet.getLong("id");
            }

            return credentialsId;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

        return credentialsId;
    }

    /**
     * Method verifies the given credentials (username/card-id and password/pin)
     * it returns the user object on success and empty user objecton failure.
     *
     * @param credentials username/card-id and password/pin
     * @return user
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public User verifyCredentials(Credentials credentials) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        User user = new User();
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * " +
                    "FROM credentials " +
                    "JOIN users ON credentials.id = users.credentials_id " +
                    "WHERE credentials.username = '" + credentials.getUsername() + "' " +
                    "AND credentials.password = '" + credentials.getPassword() + "'";

            resultSet = statement.executeQuery(sql);

            user.setLoggedIn(false);

            while (resultSet.next()) {
                user.setLoggedIn(true);
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                //user.setAddress(resultSet.getString("user_address"));
                //user.setClientId(resultSet.getInt("id"));
            }

            //Set the timestamp user-last-logged-in when successfully logged in
            if (user.isLoggedIn()){
                //updateRecord(statement, user.getClientId());
            }

            return user;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

        return user;
    }

    /**
     * Method to return the logged in user's data upon need. It returns either the requested user's data,
     * in case it is found, Or empty object, in case of failure.
     *
     * @param username contains user's username
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public User returnUser(String username) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        User user = new User();

        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT * " +
                            "FROM client " +
                            "WHERE username_card_id = '" + username + "'");

            while (resultSet.next()) {
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
            }

            return user;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {

            connection.close();

        }

        return user;
    }

    /**
     * Set the timestamp user-last-logged-in when successfully logged in
     * @param stmt SQL Stmt to update timestamp
     * @param id Row id
     *
     * @return updated row id
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    private int updateRecord(Statement stmt, int id) throws SQLException {
        String updateRowWithTimestamp = "UPDATE client " +
                "SET last_logged_in = NOW() " +
                "WHERE id = " + id;
        return stmt.executeUpdate(
                updateRowWithTimestamp);
    }


    /** ONLY DONE FOR UNIT-TESTING PURPOSES.*/
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
