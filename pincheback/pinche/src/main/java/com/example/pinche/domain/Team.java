package com.example.pinche.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "groupid")
    private String groupid;
    @Column(name = "userid")
    private String userid;
    @Column(name = "ifcaptain")
    private int ifcaptain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getIfcaptain() {
        return ifcaptain;
    }

    public void setIfcaptain(int ifcaptain) {
        this.ifcaptain = ifcaptain;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", groupid='" + groupid + '\'' +
                ", userid='" + userid + '\'' +
                ", ifcaptain=" + ifcaptain +
                '}';
    }
}
