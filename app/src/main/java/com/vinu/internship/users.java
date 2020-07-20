package com.vinu.internship;


public class users {
    private String name;
    private String phone;
    private String emailp;
    private String addressp;
    private String skills;
    private String lang;
    private String edu_10;
    private String edu_12;
    private String edu_col;
    private String pro1n;
    private String pro1d;
    private String pro2n;
    private String pro2d;
    private String exp;


    public users(){

    }

    public users(String name, String phone, String emailp, String addressp, String skills, String lang, String edu_10, String edu_12, String  edu_col, String pro1n, String pro1d, String pro2n, String pro2d, String exp){
        this.name=name;
        this.phone=phone;
        this.emailp=emailp;
        this.addressp=addressp;
        this.skills=skills;
        this.lang=lang;
        this.edu_10=edu_10;
        this.edu_12=edu_12;
        this.edu_col=edu_col;
        this.pro1n=pro1n;
        this.pro1d=pro1d;
        this.pro2n=pro2n;
        this.pro2d=pro2d;
        this.exp=exp;
    }
    public String getName(){ return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone(){return phone;}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressp() {return addressp;}

    public void setAddressp(String addressp) {
        this.addressp = addressp;
    }

    public String getEmailp() {return emailp;}

    public void setEmailp(String emailp) {
        this.emailp = emailp;
    }

    public String getSkills(){return skills; }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getEdu_10() {
        return edu_10;
    }

    public void setEdu_10(String edu_10) {
        this.edu_10 = edu_10;
    }

    public String getEdu_12() {
        return edu_12;
    }

    public void setEdu_12(String edu_12) {
        this.edu_12 = edu_12;
    }

    public String getEdu_col() {
        return edu_col;
    }

    public void setEdu_col(String edu_col) {
        this.edu_col = edu_col;
    }

    public String getPro1n() {
        return pro1n;
    }

    public void setPro1n(String pro1n) {
        this.pro1n = pro1n;
    }

    public String getPro1d() {
        return pro1d;
    }

    public void setPro1d(String pro1d) {
        this.pro1d = pro1d;
    }

    public String getPro2n() {
        return pro2n;
    }

    public void setPro2n(String pro2n) {
        this.pro2n = pro2n;
    }

    public String getPro2d() {
        return pro2d;
    }

    public void setPro2d(String pro2d) {
        this.pro2d = pro2d;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}

