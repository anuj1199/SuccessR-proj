package com.vinu.internship;

public class Intern {
    private String Field;
    private String LastDate;
    private String Qualification;
    private String UploadDate;
    private String Vacancy;

    public Intern(String field, String lastDate, String qualification, String uploadDate, String vacancy) {
        Field = field;
        LastDate = lastDate;
        Qualification = qualification;
        UploadDate = uploadDate;
        Vacancy = vacancy;
    }

    public Intern() {
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getLastDate() {
        return LastDate;
    }

    public void setLastDate(String lastDate) {
        LastDate = lastDate;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(String uploadDate) {
        UploadDate = uploadDate;
    }

    public String getVacancy() {
        return Vacancy;
    }

    public void setVacancy(String vacancy) {
        Vacancy = vacancy;
    }
}
