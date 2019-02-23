package com.example.pinche.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    public  User(){}

        @Id
        private  String  userid;

        @Column(name = "name")
        private  String name;

        @Column(name = "phone")
        private  String phone;

        @Column(name = "learnid")
        private  String learnid;

        @Column(name = "qq")
        private String qq;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
         public String toString() {
        return "user{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", learnid='" + learnid + '\'' +
                '}';
         }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLearnid() {
        return learnid;
    }

    public void setLearnid(String learnid) {
        this.learnid = learnid;
    }



}
