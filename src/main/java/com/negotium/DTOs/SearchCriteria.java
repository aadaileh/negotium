package com.negotium.DTOs;

/**
 * <h1>Data Transfer Object: Search Criteria</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   16.04.2018
 */
public class SearchCriteria {

    private String jobOrSectorPreference;
    private String minimumEducationLevel;
    private String minimumNumberOfGCSE;
    private String educationalQualification;
    private String professionalQualification;
    private String skills;
    private String experiences;

    public String getJobOrSectorPreference() {
        return jobOrSectorPreference;
    }

    public void setJobOrSectorPreference(String jobOrSectorPreference) {
        this.jobOrSectorPreference = jobOrSectorPreference;
    }

    public String getMinimumEducationLevel() {
        return minimumEducationLevel;
    }

    public void setMinimumEducationLevel(String minimumEducationLevel) {
        this.minimumEducationLevel = minimumEducationLevel;
    }

    public String getMinimumNumberOfGCSE() {
        return minimumNumberOfGCSE;
    }

    public void setMinimumNumberOfGCSE(String minimumNumberOfGCSE) {
        this.minimumNumberOfGCSE = minimumNumberOfGCSE;
    }

    public String getEducationalQualification() {
        return educationalQualification;
    }

    public void setEducationalQualification(String educationalQualification) {
        this.educationalQualification = educationalQualification;
    }

    public String getProfessionalQualification() {
        return professionalQualification;
    }

    public void setProfessionalQualification(String professionalQualification) {
        this.professionalQualification = professionalQualification;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }
}
