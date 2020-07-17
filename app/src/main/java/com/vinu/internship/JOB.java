package com.vinu.internship;

public class JOB {
    private String LASTDATE;
    private String FIELD;
    private String PERSON;
    private String QUALIFICATION;

    public JOB(String LastDate, String Field, String Person, String Qualification) {
        LASTDATE = LastDate;
        FIELD = Field;
        PERSON = Person;
        QUALIFICATION = Qualification;
    }

    public JOB() {
    }

    public String getLASTDATE() {
        return LASTDATE;
    }

    public void setLASTDATE(String LastDate) {
        LASTDATE = LastDate;
    }
    public String getField() {
        return FIELD;
    }

    public void setFIELD(String Field) {
        FIELD = Field;
    }
    public String getPERSON() {
        return PERSON;
    }

    public void setPERSON(String Person) {
        PERSON = Person;
    }
    public String getQUALIFICATION() {
        return QUALIFICATION;
    }

    public void setQUALIFICATION(String Qualification) {
        QUALIFICATION = Qualification;
    }
}
