package com.negotium.DTOs;

import java.util.ArrayList;

/**
 * <h1>Data Transfer Object: Response</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.04.2018
 */
public class Response {

    private int cvId;
    private int userId;
    private int credentialsId;
    private int contactInformationId;
    private int educationsId;
    private int languagesId;
    private int personalInformationId;
    private int prefferedJobId;
    private int referencesId;
    private int skillsId;
    private int workExperienceId;

    public int getContactInformationId() {
        return contactInformationId;
    }

    public void setContactInformationId(int contactInformationId) {
        this.contactInformationId = contactInformationId;
    }

    public int getEducationsId() {
        return educationsId;
    }

    public void setEducationsId(int educationsId) {
        this.educationsId = educationsId;
    }

    public int getLanguagesId() {
        return languagesId;
    }

    public void setLanguagesId(int languagesId) {
        this.languagesId = languagesId;
    }

    public int getPersonalInformationId() {
        return personalInformationId;
    }

    public void setPersonalInformationId(int personalInformationId) {
        this.personalInformationId = personalInformationId;
    }

    public int getPrefferedJobId() {
        return prefferedJobId;
    }

    public void setPrefferedJobId(int prefferedJobId) {
        this.prefferedJobId = prefferedJobId;
    }

    public int getReferencesId() {
        return referencesId;
    }

    public void setReferencesId(int referencesId) {
        this.referencesId = referencesId;
    }

    public int getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(int skillsId) {
        this.skillsId = skillsId;
    }

    public int getWorkExperienceId() {
        return workExperienceId;
    }

    public void setWorkExperienceId(int workExperienceId) {
        this.workExperienceId = workExperienceId;
    }

    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }
}
