package com.negotium.Services.Main.impl;

import com.negotium.DTOs.*;
import com.negotium.Factory.CommonFactoryAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

/**
 * <h1>Authentication service implementations</h1>
 *
 * <p>
 * Contains the implementation of all members of the Authentication-Service
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   16.04.2018
 */
@Service
public class MainServiceImplentations extends CommonFactoryAbstract {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceImplentations.class);

    @Autowired
    private DataSource dataSource;

    /**
     * <h1>Register new users</h1>
     * <p>
     * Saves users data into proper tables after user fills in the registration form.
     * Data still not valid till activating it by clicking on the activation link
     * </p>
     *
     * @param user users data
     * @return users data enhanced with additional info (token)
     * @throws SQLException
     *
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
    public User registerUser(User user) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        String token = user.getUniqid();


        try {
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            Long credentialsId = insertIntoCredentials(user, connection, token);
            int usersId = insertIntoUsers(user, connection, credentialsId);
            insertIntoContactInformation(user, connection, usersId);

            stmt.close();
            user.setUserId(usersId);
            return user;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return null;

        }
    }

    /**
     * <h1>Confirm registration</h1>
     *
     * <p>
     * Contains the implementation of all needed functions to confirm the registration via email
     * </p>
     *
     * @param code the confirmation code send via email
     * @return TRUE or FALSE
     * @throws SQLException
     *
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
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

        }
    }

    /**
     * <h1>
     * Method verifies the given credentials (username/card-id and password/pin)
     * </h1>
     *
     * <p>
     * It returns the user object on success and empty user objecton failure.
     * </p>
     *
     * @param credentials username/card-id and password/pin
     * @return user the enhanced user object
     *
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
    public User verifyCredentials(Credentials credentials) throws SQLException {

        dataSource = getDataSource();
        User user = new User();
        ResultSet resultSet = null;

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

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
        }

        return user;
    }

    /**
     * <h1>
     * Saving CV - Header Section
     * </h1>
     *
     * <p>
     * Saves the Header section to the cv table
     * </p>
     *
     * @param header all header section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
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
//            connection.close();
        }
    }

    /**
     * <h1>
     * Saving CV - Personal Information Section
     * </h1>
     *
     * <p>
     * Saves the Personal Information section to the cv table
     * </p>
     *
     * @param personalInformation all Personal Information section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
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
//            connection.close();
        }
    }

    /**
     * <h1>
     * Saving CV - Contact Information Section
     * </h1>
     *
     * <p>
     * Saves the Contact Information section to the cv table
     * </p>
     *
     * @param contactInformation all Contact Information section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
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
//            connection.close();
        }
    }

    /**
     * <h1>
     * Saving CV - Work Experience Section
     * </h1>
     *
     * <p>
     * Saves the Work Experience section to the cv table
     * </p>
     *
     * @param workExperience all work experience section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
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
//            connection.close();
        }
    }

    /**
     * <h1>
     * Saving CV - Education Section
     * </h1>
     *
     * <p>
     * Saves the Education section to the cv table
     * </p>
     *
     * @param education all education section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
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
//            connection.close();
        }
    }

    /**
     * <h1>
     * Saving CV - Language Section
     * </h1>
     *
     * <p>
     * Saves the Language section to the cv table
     * </p>
     *
     * @param language all header section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
    public Response saveCvLanguage(Language language) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(language.getUsersId());
        response.setCvId(language.getCvId());

        try {
            connection = dataSource.getConnection();

            int langId = insertIntoLanguages(language, connection);

            response.setLanguagesId(langId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
//            connection.close();
        }
    }

    /**
     * <h1>
     * Saving CV - References Section
     * </h1>
     *
     * <p>
     * Saves the References section to the cv table
     * </p>
     *
     * @param reference all reference section's fields
     * @return response contains all Ids of sections
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
    public Response saveCvReference(Reference reference) throws SQLException {

        dataSource = getDataSource();
        Connection connection = null;
        Response response = new Response();

        response.setUserId(reference.getUsersId());
        response.setCvId(reference.getCvId());

        try {
            connection = dataSource.getConnection();

            int refId = insertIntoReferences(reference, connection);

            response.setReferencesId(refId);
            return response;

        } catch (Exception e) {

            LOG.debug(e.getMessage());
            return response;

        } finally {
//            connection.close();
        }
    }

    /**
     * <h1>
     * Method to return the logged in user's data upon need.
     * </h1>
     *
     * <p>
     * It returns either the requested user's data, in case it is found, Or empty object, in case of failure.
     * </p>
     *
     * @param username contains user's username
     * @return user user's data (if success), or null in case of failure
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
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
//            connection.close();
        }

        return user;
    }

    /**
     * <h1>
     * Method searches in the available CV databases.
     * </h1>
     *
     * <P>
     *  All CV databse tables will be searched (full scan search) according searched parameters.
     * </P>
     *
     * @param searchCriteria DTO contains all criteria used for the search
     * @return List of found resumes
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
    public ArrayList<Resume> search (SearchCriteria searchCriteria) throws SQLException {

        Connection connection = getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ArrayList<Resume> resumes = new ArrayList<>();
        HashSet<Integer> cvIds = new HashSet<Integer>();

        try {

            if(searchCriteria.getJobOrSectorPreference() != null && !searchCriteria.getJobOrSectorPreference().isEmpty()) {
                String sql = "SELECT id FROM cv WHERE preffered_job like '%" + searchCriteria.getJobOrSectorPreference() + "%';";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();
            }

            if(searchCriteria.getMinimumEducationLevel() != null && !searchCriteria.getMinimumEducationLevel().isEmpty()) {
                String sql = "SELECT id FROM cv WHERE education >= " + searchCriteria.getMinimumEducationLevel() + ";";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();
            }

            if(searchCriteria.getMinimumNumberOfGCSE() != null && !searchCriteria.getMinimumNumberOfGCSE().isEmpty()) {
                String sql = "SELECT id FROM cv WHERE gcse >= " + searchCriteria.getMinimumNumberOfGCSE() + ";";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();
            }

            String educationalQualification = searchCriteria.getEducationalQualification();
            if(educationalQualification != null && !educationalQualification.isEmpty()) {
                //search in table 'cv'
                String sqlCV = "SELECT id FROM cv WHERE education LIKE '%" + educationalQualification + "%';";
                ResultSet resultSet = statement.executeQuery(sqlCV);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();

                //search in table 'educations'
                String sqlEducation = "SELECT id FROM educations WHERE " +
                        "major LIKE '%" + educationalQualification + "%' " +
                        "OR description LIKE '%" + educationalQualification + "%';";
                ResultSet resultSetEducations = statement.executeQuery(sqlEducation);
                while (resultSetEducations.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSetEducations.close();
            }

            String professionalQualification = searchCriteria.getProfessionalQualification();
            if(professionalQualification != null && !professionalQualification.isEmpty()) {
                //search in table 'cv'
                String sqlCV = "SELECT id FROM cv WHERE experience LIKE '%" + professionalQualification + "%';";
                ResultSet resultSet = statement.executeQuery(sqlCV);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();

                //search in table 'work_experiences'
                String sqlEducation = "SELECT id FROM work_experiences WHERE " +
                        "title LIKE '%" + professionalQualification + "%' " +
                        "OR description LIKE '%" + professionalQualification + "%';";
                ResultSet resultSetWorkExperiences = statement.executeQuery(sqlEducation);
                while (resultSetWorkExperiences.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSetWorkExperiences.close();
            }

            String skills = searchCriteria.getSkills();
            if(skills != null && !skills.isEmpty()) {
                String sql = "SELECT id FROM cv WHERE skills LIKE '%" + skills + "%';";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();
            }

            String experiences = searchCriteria.getExperiences();
            if(experiences != null && !experiences.isEmpty()) {
                //search in table 'cv'
                String sqlCV = "SELECT id FROM cv WHERE experience LIKE '%" + experiences + "%';";
                ResultSet resultSet = statement.executeQuery(sqlCV);
                while (resultSet.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSet.close();

                //search in table 'work_experiences'
                String sqlEducation = "SELECT id FROM work_experiences WHERE " +
                        "title LIKE '%" + experiences + "%' " +
                        "OR description LIKE '%" + experiences + "%';";
                ResultSet resultSetExperiences = statement.executeQuery(sqlEducation);
                while (resultSetExperiences.next()) {
                    cvIds.add(resultSet.getInt("id"));
                }
                resultSetExperiences.close();
            }

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            statement.close();
            connection.close();
        }

        for (int cvId : cvIds) {
            ResumeComplete cv = getCv(cvId);

            if(cv.getName() != null && !cv.getName().isEmpty() && cv.getSurname() != null && !cv.getSurname().isEmpty()
                    && (
                    (cv.getMobile() != null && !cv.getMobile().isEmpty()) ||
                            (cv.getWebsite() != null && !cv.getWebsite().isEmpty()) ||
                            (cv.getEmail() != null && !cv.getEmail().isEmpty())
            )
                    ) {
                Resume resume = new Resume();
                resume.setCvId(cvId);
                resume.setName(cv.getName() + " " + cv.getSurname());
                resume.setContactData("Email: " + cv.getEmail() + ", Mobile: " + cv.getMobile() + ", Website: " + cv.getWebsite());
                resume.setEducation(cv.getEducation());
                resume.setExperience(cv.getExperience());

                resumes.add(resume);
            }
        }

        return resumes;
    }



    /**
     * <h1>
     *     Method Returns the whole CV data based on the given ID
     * </h1>
     *
     * <p>
     *     All fields are collected and returned based on the given cv-Id. This is used for the PDF creation functions.
     *     And in the search
     * </p>
     *
     * @param cvId The ID of the requested CV
     * @return All fields of a CV
     * @throws SQLException
     *
     * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     * @version 1.0
     * @since   16.04.2018
     */
    public ResumeComplete getCv (int cvId) throws SQLException {

        Connection connection = getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResumeComplete resumeComplete = new ResumeComplete();
        resumeComplete.setCvId(cvId);

        try {

            //users
            String usersSql = "SELECT * FROM users WHERE cv_id = '" + cvId + "';";
            ResultSet usersResultSet = statement.executeQuery(usersSql);
            while (usersResultSet.next()) {
                resumeComplete.setName(usersResultSet.getString("name"));
                resumeComplete.setSurname(usersResultSet.getString("surname"));
            }
            usersResultSet.close();

            //cv (header)
            String CVSql = "SELECT * FROM cv WHERE id = '" + cvId + "';";
            ResultSet CVResultSet = statement.executeQuery(CVSql);
            while (CVResultSet.next()) {
                resumeComplete.setTitle(CVResultSet.getString("title"));
                resumeComplete.setCreationDate(CVResultSet.getString("created_date_time"));
                resumeComplete.setEducation(CVResultSet.getString("education"));
                resumeComplete.setLocation(CVResultSet.getString("location"));
                resumeComplete.setGcse(CVResultSet.getString("gcse"));
                resumeComplete.setSkills(CVResultSet.getString("skills"));
                resumeComplete.setExperience(CVResultSet.getString("experience"));
                resumeComplete.setPrefferedJob(CVResultSet.getString("preffered_job"));
            }
            CVResultSet.close();

            //contact_information
            String CIsql = "SELECT * FROM contact_information WHERE cv_id = '" + cvId + "';";
            ResultSet CIresultSet = statement.executeQuery(CIsql);
            while (CIresultSet.next()) {
                resumeComplete.setEmail(CIresultSet.getString("email"));
                resumeComplete.setMobile(CIresultSet.getString("mobile"));
                resumeComplete.setWebsite(CIresultSet.getString("website"));
            }
            CIresultSet.close();

            //personal_information
            String personalInfoSQL = "SELECT * FROM personal_information WHERE cv_id = '" + cvId + "';";
            ResultSet personalInfoResultSet = statement.executeQuery(personalInfoSQL);
            while (personalInfoResultSet.next()) {
                resumeComplete.setBirthdate(personalInfoResultSet.getString("birthdate"));
                resumeComplete.setGender(personalInfoResultSet.getString("gender"));
                resumeComplete.setNationality(personalInfoResultSet.getString("nationality"));
                resumeComplete.setResidenceCountry(personalInfoResultSet.getString("residence_country"));
                resumeComplete.setMaritalStatus(personalInfoResultSet.getString("marital_status"));
                resumeComplete.setNumberOfDependencies(personalInfoResultSet.getString("number_of_dependencies"));
            }
            personalInfoResultSet.close();

            //education
            String educationSQL = "SELECT * FROM educations WHERE cv_id = '" + cvId + "';";
            ResultSet educationResultSet = statement.executeQuery(educationSQL);
            while (educationResultSet.next()) {
                resumeComplete.setInstitution(educationResultSet.getString("institution"));
                resumeComplete.setDegree(educationResultSet.getString("degree"));
                resumeComplete.setMajor(educationResultSet.getString("major"));
                resumeComplete.setCompletionDate(educationResultSet.getString("completion_date_time"));
                resumeComplete.setCountry(educationResultSet.getString("country"));
                resumeComplete.setCity(educationResultSet.getString("city"));
                resumeComplete.setGrade(educationResultSet.getString("grade"));
                resumeComplete.setEducationDescription(educationResultSet.getString("description"));
            }
            educationResultSet.close();

            //languages
            String languageSQL = "SELECT * FROM languages WHERE cv_id = '" + cvId + "';";
            ResultSet languageResultSet = statement.executeQuery(languageSQL);
            while (languageResultSet.next()) {
                resumeComplete.setLanguage(languageResultSet.getString("language"));
                resumeComplete.setLanguageLevel(languageResultSet.getString("lang_level"));
            }
            languageResultSet.close();

            //references
            String referencesSQL = "SELECT * FROM cv_references WHERE cv_id = '" + cvId + "';";
            ResultSet referencesResultSet = statement.executeQuery(referencesSQL);
            while (referencesResultSet.next()) {
                resumeComplete.setReferenceName(referencesResultSet.getString("ref_name"));
                resumeComplete.setReferenceJobTitle(referencesResultSet.getString("job_title"));
                resumeComplete.setReferenceCompanyName(referencesResultSet.getString("company_name"));
                resumeComplete.setReferencePhone(referencesResultSet.getString("phone"));
                resumeComplete.setReferenceEmail(referencesResultSet.getString("email"));
            }
            referencesResultSet.close();

            //work_experiences
            String workExperienceSQL = "SELECT * FROM work_experiences WHERE cv_id = '" + cvId + "';";
            ResultSet workExperienceResultSet = statement.executeQuery(workExperienceSQL);
            while (workExperienceResultSet.next()) {
                resumeComplete.setStartDate(workExperienceResultSet.getString("start_date_time"));
                resumeComplete.setEndDate(workExperienceResultSet.getString("end_date_time"));
                resumeComplete.setWorkTitle(workExperienceResultSet.getString("title"));
                resumeComplete.setEmployer(workExperienceResultSet.getString("employer"));
                resumeComplete.setWorkDescription(workExperienceResultSet.getString("description"));
            }
            workExperienceResultSet.close();

            return resumeComplete;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            statement.close();
            connection.close();
        }

        return resumeComplete;
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
                pStmtCredentials.close();
                throw new SQLException("Creating credentials failed, no ID obtained.");
            }
        }

        pStmtCredentials.close();
        return credentialsId;
    }

    private int insertIntoUsers(User user, Connection connection, Long credentialsId) throws SQLException {

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

        int usersId;
        try (ResultSet generatedKeys = pStmtUsers.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                usersId = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating users failed, no ID obtained.");
            }
        }

        return usersId;
    }

    private Long insertIntoContactInformation(User user, Connection connection, int usersId) throws SQLException {

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
        String credentialsStmt = "INSERT INTO cv (title, education, gcse, skills, location, " +
                "experience, preffered_job, users_id, created_date_time) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement pStmtCvHeader = connection.prepareStatement(credentialsStmt, Statement.RETURN_GENERATED_KEYS);

        pStmtCvHeader.setString(1, header.getTitle());
        pStmtCvHeader.setString(2, header.getEducation());
        pStmtCvHeader.setString(3, header.getGcse());
        pStmtCvHeader.setString(4, header.getSkills());
        pStmtCvHeader.setString(5, header.getLocation());
        pStmtCvHeader.setString(6, header.getExperience());
        pStmtCvHeader.setString(7, header.getPrefferedJob());
        pStmtCvHeader.setInt(8, header.getUsersId());
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        pStmtCvHeader.setDate(9, date);

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
        Statement statement2 = connection.createStatement();
        ResultSet CIresultSet = statement2.executeQuery(CIsql);

        int ciId = 0;
        while(CIresultSet.next()){
            ciId  = CIresultSet.getInt("id");
        }
        CIresultSet.close();

        return ciId;
    }

    private int insertIntoWorkExperiences(WorkExperience we, Connection connection) throws SQLException {
        String workExperienceStmt = "INSERT INTO work_experiences (we_order, start_date_time, end_date_time, title, employer, cv_id, description) " +
                "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement pStmtCvWE = connection.prepareStatement(workExperienceStmt, Statement.RETURN_GENERATED_KEYS);

        int order = 0;
        if (we.getOrder() != 0) { order = we.getOrder();} else {order = 1;}

        pStmtCvWE.setInt(1, order);
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

        int order = 0;
        if (edu.getOrder() != 0) { order = edu.getOrder();} else {order = 1;}

        pStmtCvEducation.setInt(1, order);
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

        int order = 0;
        if (language.getOrder() != 0) { order = language.getOrder();} else {order = 1;}

        pStmtCvLanguage.setInt(1, order);
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

        int order = 0;
        if (reference.getOrder() != 0) { order = reference.getOrder();} else {order = 1;}

        pStmtCvReference.setInt(1, order);
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
            while (CIresultSet.next()) { user.setContactInformationId(CIresultSet.getInt("id"));}

            String educationSQL = "SELECT * FROM education WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet educationResultSet = statement.executeQuery(educationSQL);
            while (educationResultSet.next()) { user.setEducationsId(educationResultSet.getInt("id"));}

            String languageSQL = "SELECT * FROM languages WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet languageResultSet = statement.executeQuery(languageSQL);
            while (languageResultSet.next()) { user.setLanguagesId(languageResultSet.getInt("id"));}

            String personalInfoSQL = "SELECT * FROM personal_information WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet personalInfoResultSet = statement.executeQuery(personalInfoSQL);
            while (personalInfoResultSet.next()) { user.setPersonalInformationId(personalInfoResultSet.getInt("id"));}

            String referencesSQL = "SELECT * FROM references WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet referencesResultSet = statement.executeQuery(referencesSQL);
            while (referencesResultSet.next()) { user.setReferencesId(referencesResultSet.getInt("id"));}

            String workExperienceSQL = "SELECT * FROM work_experiences WHERE cv_id = '" + user.getCvId() + "';";
            ResultSet workExperienceResultSet = statement.executeQuery(workExperienceSQL);
            while (workExperienceResultSet.next()) { user.setWorkExperienceId(workExperienceResultSet.getInt("id"));}

            return user;

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
        }

        return user;
    }


    private int getContactInformationId (Statement statement, int usersId) throws SQLException {

        int ciId = 0;
        try {
            String stmt = "SELECT * FROM contact_information WHERE users_id = '" + usersId + "';";
            ResultSet CIresultSet = statement.executeQuery(stmt);
            ciId = CIresultSet.getInt("id");

        } catch (Exception e) {

            LOG.debug(e.getMessage());

        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
        }

        return ciId;
    }

    /** ONLY DONE FOR UNIT-TESTING PURPOSES.*/
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
