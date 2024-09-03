package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @Column(name = "passenger_id", nullable = false, length = 244)
    private String passengerId;

    @Column(name = "nrc_card", length = 244)
    private String nrcCard;

    @Column(name = "fullname", length = 244)
    private String fullname;

    @Column(name = "email", length = 244)
    private String email;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "passenger_type", length = 244)
    private String passengerType;

    @Column(name = "bus_id", length = 244)
    private String busId;

    @Column(name = "status", length = 244)
    private String status;

    @Column(name = "psg_type", length = 244)
    private String psgType;

    @Column(name = "request", length = 244)
    private String request;

    @ManyToOne
    @JoinColumn(name = "bus_id", insertable = false, updatable = false)
    private Bus bus;

    // Getters and Setters
    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getNrcCard() {
        return nrcCard;
    }

    public void setNrcCard(String nrcCard) {
        this.nrcCard = nrcCard;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPsgType() {
        return psgType;
    }

    public void setPsgType(String psgType) {
        this.psgType = psgType;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}

