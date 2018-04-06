package com.example.lab08.studentgrade;

/**
 * Created by Lab08 on 6.04.2018.
 */

public class PersonModel {
    String gender;
    String name;
    String studentNumber;
    String lesson;
    String grade;



    public PersonModel(String gender, String name, String studentNumber, String grade,String lesson) {
        this.gender = gender;
        this.name = name;
        this.studentNumber = studentNumber;
        this.lesson = lesson;
        this.grade = grade;

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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
