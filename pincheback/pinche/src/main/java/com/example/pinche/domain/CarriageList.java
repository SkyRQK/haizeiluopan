package com.example.pinche.domain;



import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@Table(name = "carriagelist")
@DynamicInsert
@DynamicUpdate
public class CarriageList {

    public CarriageList(){}

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "carnum")
    private String carnum;

    @Column(name = "userid")
    private String userid;

    @Column(name = "starttime")
    private String starttime;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "qq")
    private String qq;


    private String trueName;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    @Override
    public String toString() {
        return "CarriageList{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", carnum='" + carnum + '\'' +
                ", userid='" + userid + '\'' +
                ", starttime='" + starttime + '\'' +
                ", remarks='" + remarks + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
