package com.negotium.DTOs;

/**
 * <h1>Data Transfer Object: User</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   12.04.2018
 */
public class User {

    private int userId;
    private int credentialsId;
    private int cvId;

    private int contactInformationId;
    private int educationsId;
    private int languagesId;
    private int personalInformationId;
    private int prefferedJobId;
    private int referencesId;
    private int skillsId;
    private int workExperienceId;

    private String type;
    private int valid;
    private String team_name;
    private String name;
    private String surname;
    private String email;
    private String member_number;
    private String tel;
    private String uniqid;
    private String password;
    private boolean loggedIn;

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

    public int getCvId() {
        return cvId;
    }

    public void setCvId(int cvId) {
        this.cvId = cvId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUniqid() {
        return uniqid;
    }

    public void setUniqid(String uniqid) {
        this.uniqid = uniqid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
