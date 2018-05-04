package com.negotium.DTOs;

/**
 * <h1>Data Transfer Object: CV Headers</h1>
 *
 * <p>
 * Contains all related attributes and their getters and setters
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   16.04.2018
 */
public class Header {

    private String title;
    private String location;
    private String education;
    private String gcse;
    private String skills;
    private String experience;
    private String prefferedJob;
    private String photo;
    private int usersId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGcse() {
        return gcse;
    }

    public void setGcse(String gcse) {
        this.gcse = gcse;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPrefferedJob() {
        return prefferedJob;
    }

    public void setPrefferedJob(String prefferedJob) {
        this.prefferedJob = prefferedJob;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
}
