package com.negotium.DTOs;

/**
 * <h1>Data Transfer Object: Returnes the complete data of a resume</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   16.04.2018
 */
public class ResumeComplete {

    private int cvId;

    //users
    private String name;
    private String surname;

    //cv
    private String title;
    private String creationDate;
    private String education;
    private String location;
    private String experience;
    private String prefferedJob;

    //contact_information
    private String email;
    private String mobile;
    private String website;

    //personal_information
    private String birthdate;
    private String gender;
    private String nationality;
    private String residenceCountry;
    private String maritalStatus;
    private String numberOfDependencies;

    //work_experiences
    private String startDate;
    private String endDate;
    private String workTitle;
    private String employer;
    private String workDescription;

    //educations
    private String institution;
    private String degree;
    private String major;
    private String completionDate;
    private String country;
    private String city;
    private String grade;
    private String educationDescription;

    //references
    private String referenceName;
    private String referenceJobTitle;
    private String referenceCompanyName;
    private String referencePhone;
    private String referenceEmail;



}