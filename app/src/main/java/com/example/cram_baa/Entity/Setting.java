package com.example.cram_baa.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class Setting implements Serializable{
    private static final long serialVersionUID = -8835186325316098956L;
    private Integer ID;
    private String name;
    private String sex;
    private String region;
    private String age;
    private String school;
    private String grade;
    private String clbum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClbum() {
        return clbum;
    }

    public void setClbum(String clbum) {
        this.clbum = clbum;
    }
}
