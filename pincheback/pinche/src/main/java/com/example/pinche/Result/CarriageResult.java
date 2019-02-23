package com.example.pinche.Result;

import com.example.pinche.domain.CarriageList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Table(name = "")
public class CarriageResult {

    @Column(name = "userid")
    private String userid;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "qq")
    private String qq;

    @Override
    public String toString() {
        return "CarriageResult{" +
                "userid='" + userid + '\'' +
                ", remarks='" + remarks + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
