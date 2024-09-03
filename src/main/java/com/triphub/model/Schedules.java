package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedules {

    @Id
    @Column(name = "schedule_id", nullable = false, length = 244)
    private String scheduleId;

    @Column(name = "bus_id", length = 244)
    private String busId;

    @Column(name = "route_id", length = 244)
    private String routeId;

    @Column(name = "departure_time")
    private java.sql.Timestamp departureTime;

    @Column(name = "arrival_time")
    private java.sql.Timestamp arrivalTime;

    @Column(name = "travel_date")
    private java.sql.Date travelDate;

    @ManyToOne
    @JoinColumn(name = "bus_id", insertable = false, updatable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id", insertable = false, updatable = false)
    private Route route;

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public java.sql.Timestamp getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(java.sql.Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	public java.sql.Timestamp getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(java.sql.Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public java.sql.Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(java.sql.Date travelDate) {
		this.travelDate = travelDate;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

    
}

