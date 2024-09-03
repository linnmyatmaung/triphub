package com.triphub.model;

import jakarta.persistence.*;
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "order_id", nullable = false, length = 244)
    private String orderId;

    @Column(name = "passenger_id", length = 244)
    private String passengerId;

    @Column(name = "status", length = 244)
    private String status;

    @Column(name = "bus_id", length = 244)
    private String busId;

    @Column(name = "order_date")
    private java.sql.Date orderDate;

    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "bus_id", insertable = false, updatable = false)
    private Bus bus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

    // Getters and Setters
    // ...
    
}

