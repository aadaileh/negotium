package com.negotium.Services.Main.impl;

import com.negotium.Connection.DatabaseConnectionSingleton;
import com.negotium.DTOs.*;
import com.negotium.Factory.CommonFactoryAbstract;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceImplentationsTest {

    private final MainServiceImplentations cut = new MainServiceImplentations();

    private User user = new User();
    final static private String NAME = "bar";
    final static private String EMAIL = "foo";
    final static private String EMPLOYER = "foo";
    final static private String DEGREE = "bla";
    final static private String LANGUAGE = "foo";
    final static private String COMPANYNAME = "foo";
    final static private String USERNAME = "foo";
    final static private String EDUCATIONQUALIFICATION = "foo";
    final static private String MINEDUCATIONLEVEL = "foo";
    final static private int CVID = 1;
    final static private int USERID = 1;
    final static private String GENDER = "foo";

    @Mock
    DataSource dataSourceMock;

    @Mock
    Connection connectionMock;

    @Mock
    DatabaseConnectionSingleton databaseConnectionSingletonMock;

    @Mock
    CommonFactoryAbstract commonFactoryAbstractMock;

    @Mock
    Statement statementMock;

    @Before
    public void setUp() throws Exception {

        user.setName(NAME);
        user.setCvId(CVID);
        user.setUniqid(UUID.randomUUID().toString());

        cut.setDataSource(dataSourceMock);

        when(databaseConnectionSingletonMock.dataSource()).thenReturn(dataSourceMock);
        when(commonFactoryAbstractMock.getDataSource()).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(connectionMock);
        when(connectionMock.createStatement()).thenReturn(statementMock);
    }

    @Test
    public void registerUserTest() throws SQLException {

        User user = cut.registerUser(this.user);
        this.user.getUserId();

        assert(!this.user.getUniqid().isEmpty());
    }

    @Test
    public void confirmTest() throws SQLException {

        Boolean confirm = cut.confirm("foo");
        assertNull(confirm);
    }

    @Test
    public void verifyCredentialsTest() throws SQLException {

        Credentials credentials = new Credentials();
        credentials.setPassword("foo");
        credentials.setUsername("bar");

        cut.verifyCredentials(credentials);
        assert(!this.user.getUniqid().isEmpty());
    }

    @Test
    public void saveCvHeaderTest() throws SQLException {

        Header header = new Header();
        header.setEducation("foo");
        header.setUsersId(USERID);

        Response response = cut.saveCvHeader(header);
        assertNotNull(response.getCvId());
    }

    @Test
    public void saveCvPersonalInformationTest() throws SQLException {

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setCvId(CVID);
        personalInformation.setGender(GENDER);

        Response response = cut.saveCvPersonalInformation(personalInformation);
        assertNotNull(response.getCvId());
    }

    @Test
    public void saveCvContactInformationTest() throws SQLException {

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setCvId(CVID);
        contactInformation.setEmail(EMAIL);

        Response response = cut.saveCvContactInformation(contactInformation);
        assertNotNull(response.getCvId());
    }

    @Test
    public void saveCvWorkExperienceTest() throws SQLException {

        WorkExperience workExperience = new WorkExperience();
        workExperience.setCvId(CVID);
        workExperience.setEmployer(EMPLOYER);

        Response response = cut.saveCvWorkExperience(workExperience);
        assertNotNull(response.getCvId());
    }

    @Test
    public void saveCvEducationTest() throws SQLException {

        Education education = new Education();
        education.setCvId(CVID);
        education.setDegree(DEGREE);

        Response response = cut.saveCvEducation(education);
        assertNotNull(response.getCvId());
    }

    @Test
    public void saveCvLanguageTest() throws SQLException {

        Language language = new Language();
        language.setCvId(CVID);
        language.setLanguage(LANGUAGE);

        Response response = cut.saveCvLanguage(language);
        assertNotNull(response.getCvId());
    }

    @Test
    public void saveCvReferenceTest() throws SQLException {

        Reference reference = new Reference();
        reference.setCvId(CVID);
        reference.setCompanyName(COMPANYNAME);

        Response response = cut.saveCvReference(reference);
        assertNotNull(response.getCvId());
    }

    @Test
    public void returnUserTest() throws SQLException {

        User user = cut.returnUser(USERNAME);
        assertNotNull(this.user.getUniqid());
    }

}
