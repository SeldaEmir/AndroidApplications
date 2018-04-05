package com.example.selda.customlist;



public class PersonModel {
    String gender;
    String name;
    String age;
    String funTeam;
    String homeTown;
    String graduated;

    public PersonModel() {
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public PersonModel(String gender, String name, String age, String funTeam, String homeTown, String graduated) {
        this.gender = gender;
        this.name = name;
        this.age = age;
        this.funTeam = funTeam;
        this.homeTown = homeTown;
        this.graduated=graduated;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFunTeam() {
        return funTeam;
    }

    public void setFunTeam(String funTeam) {
        this.funTeam = funTeam;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
