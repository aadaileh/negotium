package com.negotium.MainService;

import com.negotium.DTOs.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1>Main Service Interface for the Coursework: ABC Banking Group</h1>
 *
 * <p>
 * Here is the declaration and definition of all methods implemented in the
 * related controller. It is always the first point to get requests and
 * distribute them to different other services. The whole communication is
 * done based on the NetFlix Feign Client. This service negotiate with:
 * Authentication-service, Account-service and Transaction-service.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
public interface MainServiceInterface {


    /**
     * <h1>Register new users</h1>
     *
     * <p>
     * Register new job-seekers to NEGOTIUM. This method saves entered data to
     * both tables: credentials, users, contact_information. It does generate a UUID token
     * that will be used in the confirmation process.
     * </p>
     *
     * @param user users data
     * @return users data enhanced with additional info (token)
     * @throws SQLException
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public int registerUser(User user) throws SQLException;

    /**
     * <h1>Confirm the new job-seekers registered to NEGOTIUM.</h1>
     *
     * <p>
     * This method checks the code
     * taken from link and compare it to the one in db, if matches, returns ok, otherwise
     * false.
     * </p>
     *
     * @return boolean true if matches, false if not

     * @throws SQLException
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Boolean confirm(@PathVariable String code) throws SQLException;


    /**
     * Verify the credentials by checking the database table credentials.
     *
     * @return User if credentials are coorect, or NULL if not
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public User login(@RequestBody Credentials credentials) throws SQLException;

    /**
     * Save the CV Header
     *
     * @return cv-id, the created CV-ID
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvHeader(@RequestBody Header header) throws SQLException;

    /**
     * Save the CV Personal Information
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvPersonalInformation(@RequestBody PersonalInformation personalInformation) throws SQLException;

    /**
     * Save the CV Contact Information
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvContactInformation(@RequestBody ContactInformation contactInformation) throws SQLException;

    /**
     * Save the CV Work experience
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvWorkExperience(@RequestBody WorkExperience workExperience) throws SQLException;

    /**
     * Saves the CV Education Section
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvEducation(@RequestBody Education education) throws SQLException;
    /**
     * Saves the CV Language Section
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvLanguage(@RequestBody Language language) throws SQLException;

    /**
     * Saves the CV References Section
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public Response saveCvReference(@RequestBody Reference reference) throws SQLException;

    /**
     * Search CVs according several criteria
     *
     * @return Resumes contains all related searched fields
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public ArrayList<Resume> search(@RequestBody SearchCriteria searchCriteria) throws SQLException;


    /**
     * Return the whole CV data based on the given ID
     *
     * @return Resumes contains all related searched fields
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    public ResumeComplete getCv(@PathVariable int id) throws SQLException;
}
