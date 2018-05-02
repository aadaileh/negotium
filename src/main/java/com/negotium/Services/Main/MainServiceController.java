package com.negotium.Services.Main;

import com.negotium.DTOs.*;
import com.negotium.Factory.CommonFactoryAbstract;
import com.negotium.Services.Main.impl.MainServiceImplentations;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1>Main Service for the Coursework: NEGOTIUM</h1>
 * <p>
 * It is always the first point to get requests and distribute them to different other services.
 * The whole communication is done based on the NetFlix Feign Client. This service negotiate with: Authentication-service
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   16.04.2018
 */
@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableSwagger2
public class MainServiceController extends CommonFactoryAbstract implements MainServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceController.class);

    @Autowired
    private MainServiceImplentations mainServiceImplentations;

    /**
     * <h1>Register new users</h1>
     *
     * <p>
     * Register new job-seekers to NEGOTIUM. This method saves entered data to
     * both tables: credentials, users, contact_information. It does generate a UUID token
     * that will be used in the confirmation process.
     * </p>
     *
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Register job-seekers to NEGOTIUM")
    @RequestMapping(value = "/negotium/api/register",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public int registerUser(@RequestBody User user) throws SQLException {

        return mainServiceImplentations.registerUser(user).getUserId();
    }

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
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Confirm job-seekers registration to NEGOTIUM")
    @RequestMapping(value = "/negotium/api/confirm/{code}",
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Boolean confirm(@PathVariable String code) throws SQLException {

        Boolean credentialsId = mainServiceImplentations.confirm(code);
        return credentialsId;
    }

    /**
     * Verify the credentials by checking the database table credentials.
     *
     * @return User if credentials are coorect, or NULL if not
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Verify the credentials of job-seekers and recruitment agencies")
    @RequestMapping(value = "/negotium/api/login/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public User login(@RequestBody Credentials credentials) throws SQLException {
        User user = mainServiceImplentations.verifyCredentials(credentials);
        return user;
    }

    /**
     * Save the CV Header
     *
     * @return cv-id, the created CV-ID
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (Header) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/header/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvHeader(@RequestBody Header header) throws SQLException {
        Response response = mainServiceImplentations.saveCvHeader(header);
        response.setUserId(header.getUsersId());

        return response;
    }

    /**
     * Save the CV Personal Information
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (Personal Information) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/personal-information/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvPersonalInformation(@RequestBody PersonalInformation personalInformation) throws SQLException {
        Response response = mainServiceImplentations.saveCvPersonalInformation(personalInformation);
        return response;
    }

    /**
     * Save the CV Contact Information
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (Contact Information) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/contact-information/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvContactInformation(@RequestBody ContactInformation contactInformation) throws SQLException {
        Response response = mainServiceImplentations.saveCvContactInformation(contactInformation);
        return response;
    }

    /**
     * Save the CV Work experience
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (Work Experiences) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/work-experience/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvWorkExperience(@RequestBody WorkExperience workExperience) throws SQLException {
        Response response = mainServiceImplentations.saveCvWorkExperience(workExperience);
        return response;
    }

    /**
     * Saves the CV Education Section
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (Education) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/education/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvEducation(@RequestBody Education education) throws SQLException {
        Response response = mainServiceImplentations.saveCvEducation(education);
        return response;
    }

    /**
     * Saves the CV Language Section
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (Languages) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/language/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvLanguage(@RequestBody Language language) throws SQLException {
        Response response = mainServiceImplentations.saveCvLanguage(language);
        return response;
    }


    /**
     * Saves the CV References Section
     *
     * @return Response contains all related IDs
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Saves the (References) section of the CV")
    @RequestMapping(value = "/negotium/api/save/cv/reference/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Response saveCvReference(@RequestBody Reference reference) throws SQLException {
        Response response = mainServiceImplentations.saveCvReference(reference);
        return response;
    }

    /**
     * Search CVs according several criteria
     *
     * @return Resumes contains all related searched fields
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Search CVs according several criteria")
    @RequestMapping(value = "/negotium/api/search/cv/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public ArrayList<Resume> search(@RequestBody SearchCriteria searchCriteria) throws SQLException {
        ArrayList<Resume> resumes = mainServiceImplentations.search(searchCriteria);
        return resumes;
    }

    /**
     * Return the whole CV data based on the given ID
     *
     * @return Resumes contains all related searched fields
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Returns the whole CV data based on the given ID")
    @RequestMapping(value = "/negotium/api/cv/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public ResumeComplete getCv(@PathVariable int id) throws SQLException {
        ResumeComplete cv = mainServiceImplentations.getCv(id);
        return cv;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /** ONLY DONE FOR UNIT-TESTING PURPOSES.*/
    public void setMainServiceImplentations(MainServiceImplentations mainServiceImplentations) {
        this.mainServiceImplentations = mainServiceImplentations;
    }
}
