package com.fxs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class User {

    private String id;
    private String phone;
    private String password;
    private String salt;
    private String pic_img;
    private String ahama;   //法名
    private String name;
    private String sex;
    private String city;
    private String sign;    //签名
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reg_date;
    private String guruid;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", pic_img='" + pic_img + '\'' +
                ", ahama='" + ahama + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", reg_date=" + reg_date +
                ", guruid='" + guruid + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPic_img() {
        return pic_img;
    }

    public void setPic_img(String pic_img) {
        this.pic_img = pic_img;
    }

    public String getAhama() {
        return ahama;
    }

    public void setAhama(String ahama) {
        this.ahama = ahama;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public String getGuruid() {
        return guruid;
    }

    public void setGuruid(String guruid) {
        this.guruid = guruid;
    }

    public User(String id, String phone, String password, String salt, String pic_img, String ahama, String name, String sex, String city, String sign, String status, Date reg_date, String guruid) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.pic_img = pic_img;
        this.ahama = ahama;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.sign = sign;
        this.status = status;
        this.reg_date = reg_date;
        this.guruid = guruid;
    }
}
