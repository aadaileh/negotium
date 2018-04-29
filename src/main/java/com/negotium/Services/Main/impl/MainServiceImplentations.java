package com.negotium.Services.Main.impl;

import com.negotium.DTOs.Credentials;
import com.negotium.DTOs.*;
import com.negotium.Factory.CommonFactoryAbstract;
import org.apache.commons.codec.language.bm.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Calendar;

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

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceImplentations.class);

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
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        User user = new User();
        ResultSet resultSet = null;

        try {
            String sql = "SELECT *, users.id as table_users_id " +
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
                user.setUserId(resultSet.getInt("table_users_id"));
                user.setCvId(resultSet.getInt("cv_id"));
                user.setCredentialsId(resultSet.getInt("credentials_id"));
            }

            user = getAllIds (statement, user);

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


    public Response saveCvHeader(Header header) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        User user = new User();
        Response response = new Response();

        try {
            connection = dataSource.getConnection();

            int cvId = insertIntoCv(header, connection);
            updateUsers(header, connection, cvId);

            response.setCvId(cvId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    public Response saveCvPersonalInformation(PersonalInformation personalInformation) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(personalInformation.getUsersId());
        response.setCvId(personalInformation.getCvId());

        try {
            connection = dataSource.getConnection();

            int piId = insertIntoPersonalInformation(personalInformation, connection);
            //updateUsers(personalInformation, connection, piId);

            response.setPersonalInformationId(piId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    public Response saveCvContactInformation(ContactInformation contactInformation) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(contactInformation.getUsersId());
        response.setCvId(contactInformation.getCvId());

        try {
            connection = dataSource.getConnection();

            int ciId = updateContactInformation(contactInformation, connection);

            response.setContactInformationId(ciId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    public Response saveCvWorkExperience(WorkExperience workExperience) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(workExperience.getUsersId());
        response.setCvId(workExperience.getCvId());

        try {
            connection = dataSource.getConnection();

            int weId = insertIntoWorkExperiences(workExperience, connection);

            response.setWorkExperienceId(weId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    public Response saveCvEducation(Education education) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(education.getUsersId());
        response.setCvId(education.getCvId());

        try {
            connection = dataSource.getConnection();

            int eduId = insertIntoEducation(education, connection);

            response.setEducationsId(eduId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    public Response saveCvLanguage(Language language) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(language.getUsersId());
        response.setCvId(language.getCvId());

        try {
            connection = dataSource.getConnection();

            int eduId = insertIntoLanguages(language, connection);

            response.setEducationsId(eduId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    public Response saveCvReference(Reference reference) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(reference.getUsersId());
        response.setCvId(reference.getCvId());

        try {
            connection = dataSource.getConnection();

            int eduId = insertIntoReferences(reference, connection);

            response.setEducationsId(eduId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
            connection.close();
        }
    }

    private Long insertIntoCredentials(User user, Connection connection, String token) throws SQLException {
        String credentialsStmt = "INSERT INTO credentials (username, password, token, created_date_time) VALUES (?,?,?,?)";

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

        String usersStmt = "INSERT INTO users (type, name, surname, email, tel, credentials_id, created_date_time) " +
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

        String usersStmt = "INSERT INTO contact_information (email, mobile, users_id) " +
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


    private int insertIntoCv(Header header, Connection connection) throws SQLException {
        String credentialsStmt = "INSERT INTO cv (title, education, location, experience, users_id, created_date_time) " +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement pStmtCvHeader = connection.prepareStatement(credentialsStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvHeader.setString(1, header.getTitle());
        pStmtCvHeader.setString(2, header.getEducation());
        pStmtCvHeader.setString(3, header.getLocation());
        pStmtCvHeader.setString(4, header.getExperience());
        pStmtCvHeader.setInt(5, header.getUsersId());
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        pStmtCvHeader.setDate(6, date);

        pStmtCvHeader.executeUpdate();

        int cvId;
        try (ResultSet generatedKeys = pStmtCvHeader.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                cvId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Inserting CV-Header failed, no ID obtained.");
            }
        }

        return cvId;
    }

    private void updateUsers(Header header, Connection connection, int cvId) throws SQLException {

        String usersStmt = "UPDATE users SET cv_id = " + cvId + " WHERE id = " + header.getUsersId();
        connection.createStatement().executeUpdate(usersStmt);
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

    private int insertIntoPersonalInformation(PersonalInformation pi, Connection connection) throws SQLException {
        String credentialsStmt = "INSERT INTO personal_information (birthdate, gender, nationality, residence_country, " +
                "marital_status, number_of_dependencies, cv_id) " +
                "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement pStmtCvPI = connection.prepareStatement(credentialsStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvPI.setString(1, pi.getBirthDate());
        pStmtCvPI.setString(2, pi.getGender());
        pStmtCvPI.setString(3, pi.getNationality());
        pStmtCvPI.setString(4, pi.getResidenceCountry());
        pStmtCvPI.setString(5, pi.getMaritalStatus());
        pStmtCvPI.setInt(6, pi.getDependenciesNumber());
        pStmtCvPI.setInt(7, pi.getCvId());

        pStmtCvPI.executeUpdate();

        int piId;
        try (ResultSet generatedKeys = pStmtCvPI.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                piId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Inserting CV (Personal Information) failed, no ID obtained.");
            }
        }

        return piId;
    }

    private int updateContactInformation(ContactInformation ci, Connection connection) throws SQLException {

        String contactInformationStmt = "UPDATE contact_information SET " +
                "email = '" + ci.getEmail() + "', " +
                "website = '" + ci.getWebsite() + "', " +
                "mobile = '" + ci.getMobile() + "', " +
                "cv_id = " + ci.getCvId() +
                " WHERE users_id = " + ci.getUsersId();
        Statement statement = connection.createStatement();
        statement.executeUpdate(contactInformationStmt);

        String CIsql = "SELECT * FROM contact_information WHERE users_id = " + ci.getUsersId();
        ResultSet CIresultSet = statement.executeQuery(CIsql);
        int ciId = CIresultSet.getInt("id");

        return ciId;
    }

    private int insertIntoWorkExperiences(WorkExperience we, Connection connection) throws SQLException {
        String workExperienceStmt = "INSERT INTO work_experiences (we_order, start_date_time, end_date_time, title, employer, cv_id, description) " +
                "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement pStmtCvWE = connection.prepareStatement(workExperienceStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvWE.setInt(1, 1);
        pStmtCvWE.setString(2, we.getFrom());
        pStmtCvWE.setString(3, we.getTo());
        pStmtCvWE.setString(4, we.getTitle());
        pStmtCvWE.setString(5, we.getEmployer());
        pStmtCvWE.setInt(6, we.getCvId());
        pStmtCvWE.setString(7, we.getDescription());

        pStmtCvWE.executeUpdate();

        int weId;
        try (ResultSet generatedKeys = pStmtCvWE.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                weId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Inserting CV (Work Experiences) failed, no ID obtained.");
            }
        }

        return weId;
    }

    private int insertIntoEducation(Education edu, Connection connection) throws SQLException {
        String educationStmt = "INSERT INTO educations (edu_order, institution, degree, major, " +
                "completion_date_time, cv_id, country, city, grade, description) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pStmtCvEducation = connection.prepareStatement(educationStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvEducation.setInt(1, 1);
        pStmtCvEducation.setString(2, edu.getInstitution());
        pStmtCvEducation.setString(3, edu.getDegree());
        pStmtCvEducation.setString(4, edu.getMajor());
        pStmtCvEducation.setString(5, edu.getCompletionDate());
        pStmtCvEducation.setInt(6, edu.getCvId());
        pStmtCvEducation.setString(7, edu.getCountry());
        pStmtCvEducation.setString(8, edu.getCity());
        pStmtCvEducation.setString(9, edu.getGrade());
        pStmtCvEducation.setString(10, edu.getDescription());

        pStmtCvEducation.executeUpdate();

        int eduId;
        try (ResultSet generatedKeys = pStmtCvEducation.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                eduId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Inserting CV (Education) failed, no ID obtained.");
            }
        }

        return eduId;
    }

    private int insertIntoLanguages(Language language, Connection connection) throws SQLException {
        String languageStmt = "INSERT INTO languages (lang_order, language, lang_level, cv_id) VALUES (?,?,?,?)";

        PreparedStatement pStmtCvLanguage = connection.prepareStatement(languageStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvLanguage.setInt(1, 1);
        pStmtCvLanguage.setString(2, language.getLanguage());
        pStmtCvLanguage.setString(3, language.getLevel());
        pStmtCvLanguage.setInt(4, language.getCvId());

        pStmtCvLanguage.executeUpdate();

        int langId;
        try (ResultSet generatedKeys = pStmtCvLanguage.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                langId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Inserting CV (Languages) failed, no ID obtained.");
            }
        }

        return langId;
    }

    private int insertIntoReferences(Reference reference, Connection connection) throws SQLException {
        String referenceStmt = "INSERT INTO cv_references (ref_order, ref_name, job_title, company_name, phone, email, cv_id) VALUES (?,?,?,?,?,?,?);";

        PreparedStatement pStmtCvReference = connection.prepareStatement(referenceStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvReference.setInt(1, 1);
        pStmtCvReference.setString(2, reference.getName());
        pStmtCvReference.setString(3, reference.getTitle());
        pStmtCvReference.setString(4, reference.getCompanyName());
        pStmtCvReference.setString(5, reference.getTel());
        pStmtCvReference.setString(6, reference.getEmail());
        pStmtCvReference.setInt(7, reference.getCvId());

        pStmtCvReference.executeUpdate();

        int refId;
        try (ResultSet generatedKeys = pStmtCvReference.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                refId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Inserting CV (References) failed, no ID obtained.");
            }
        }

        return refId;
    }


    private User getAllIds (Statement statement, User user) throws SQLException {

        try {
            String CIsql = "SELECT * FROM contact_information WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet CIresultSet = statement.executeQuery(CIsql);
            user.setContactInformationId(CIresultSet.getInt("id"));

            String educationSQL = "SELECT * FROM education WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet educationResultSet = statement.executeQuery(educationSQL);
            user.setEducationsId(educationResultSet.getInt("id"));

            String languageSQL = "SELECT * FROM languages WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet languageResultSet = statement.executeQuery(languageSQL);
            user.setLanguagesId(languageResultSet.getInt("id"));

            String personalInfoSQL = "SELECT * FROM personal_information WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet personalInfoResultSet = statement.executeQuery(personalInfoSQL);
            user.setPersonalInformationId(personalInfoResultSet.getInt("id"));

            String prefferedJobSQL = "SELECT * FROM preffered_job WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet prefferedJobResultSet = statement.executeQuery(prefferedJobSQL);
            user.setPrefferedJobId(prefferedJobResultSet.getInt("id"));

            String referencesSQL = "SELECT * FROM references WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet referencesResultSet = statement.executeQuery(referencesSQL);
            user.setReferencesId(referencesResultSet.getInt("id"));

            String skillsSQL = "SELECT * FROM skills WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet skillsResultSet = statement.executeQuery(skillsSQL);
            user.setSkillsId(skillsResultSet.getInt("id"));

            String workExperienceSQL = "SELECT * FROM work_experiences WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet workExperienceResultSet = statement.executeQuery(workExperienceSQL);
            user.setWorkExperienceId(workExperienceResultSet.getInt("id"));

            return user;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
        }

        return user;
    }


    /** ONLY DONE FOR UNIT-TESTING PURPOSES.*/
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
