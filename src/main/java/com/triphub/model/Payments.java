package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @Column(name = "payment_id", nullable = false, length = 244)
    private String paymentId;

    @Column(name = "passenger_id", length = 244)
    private String passengerId;

    @Column(name = "payment_date")
    private java.sql.Date paymentDate;

    @Column(name = "payment_amount")
    private Integer paymentAmount;

    @Column(name = "payment_method", length = 244)
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public java.sql.Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(java.sql.Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

    // Getters and Setters
    // ...
    
}
