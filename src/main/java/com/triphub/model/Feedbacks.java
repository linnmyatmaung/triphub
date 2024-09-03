package com.triphub.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "feedbacks", uniqueConstraints = {
    @UniqueConstraint(columnNames = "passenger_id")
})
public class Feedbacks {

    @Id
    @Column(name = "feedback_id", nullable = false, length = 244)
    private String feedbackId;

    @Column(name = "passenger_id", length = 244)
    private String passengerId;


    @Column(name = "feedback_date")
    private Date feedbackDate;

    @Column(name = "comments", length = 500)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    
    // Getters and Setters
    // ...

    public Feedbacks() {
    }

    // Getters and Setters

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}

