package com.example.pinche.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "carlist")
public class CarList {
    public CarList(){

    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "groupid")
    private int groupid;
    @Column(name = "seats")
    private int seats;
    @Column(name="captainid")
    private String captainid;

    @Column(name = "billdate")
    private String billdate;
    @Column(name = "beginposition")
    private String beginposition;
    @Column(name = "arriveposition")
    private String arriveposition;
    @Column(name = "palyerrequir")
    private String palyerrequir;
    @Column(name = "carintroduce")
    private String carintroduce;
    @Column(name = "cleartime")
    private String cleartime;
    @Column(name = "captain")
    private String captain;

    @Column(name = "phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getCaptainid() {
        return captainid;
    }

    public void setCaptainid(String captainid) {
        this.captainid = captainid;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getBeginposition() {
        return beginposition;
    }

    public void setBeginposition(String beginposition) {
        this.beginposition = beginposition;
    }

    public String getArriveposition() {
        return arriveposition;
    }

    public void setArriveposition(String arriveposition) {
        this.arriveposition = arriveposition;
    }

    public String getPalyerrequir() {
        return palyerrequir;
    }

    public void setPalyerrequir(String palyerrequir) {
        this.palyerrequir = palyerrequir;
    }

    public String getCarintroduce() {
        return carintroduce;
    }

    public void setCarintroduce(String carintroduce) {
        this.carintroduce = carintroduce;
    }

    public String getCleartime() {
        return cleartime;
    }

    public void setCleartime(String cleartime) {
        this.cleartime = cleartime;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    @Override
    public String toString() {
        return "CarList{" +
                "groupid=" + groupid +
                ", seats=" + seats +
                ", captainid='" + captainid + '\'' +
                ", billdate='" + billdate + '\'' +
                ", beginposition='" + beginposition + '\'' +
                ", arriveposition='" + arriveposition + '\'' +
                ", palyerrequir='" + palyerrequir + '\'' +
                ", carintroduce='" + carintroduce + '\'' +
                ", cleartime='" + cleartime + '\'' +
                ", captain='" + captain + '\'' +
                '}';
    }
}
