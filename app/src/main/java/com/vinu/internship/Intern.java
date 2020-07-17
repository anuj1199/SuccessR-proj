package com.vinu.internship;

public class Intern
{
    private String Firstname,Lastname,Emailaddress,Phoneno,Password;

    public Intern() {
    }

    public Intern(String firstname, String lastname, String EMId, String phoneno,String password)
    {
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Emailaddress = EMId;
        this.Phoneno = phoneno;
        this.Password = password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmailaddress() {
        return Emailaddress;
    }

    public void setEmailaddress(String EMId) {
        Emailaddress = EMId;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

