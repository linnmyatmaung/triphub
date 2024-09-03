package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @Column(name = "bus_id", nullable = false, length = 244)
    private String busId;

    @Column(name = "VIN", length = 244)
    private String vin;

    
    @Column(name = "bus_type", length = 244)
    private String busType;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "year")
    private Integer year;

    @Column(name = "model", length = 244)
    private String model;

    @Column(name = "status", length = 244)
    private String status;

    @Column(name = "operator_id", length = 244)
    private String operatorId;

    @Column(name = "route_id", length = 244)
    private String routeId;

    @ManyToOne
    @JoinColumn(name = "operator_id", insertable = false, updatable = false)
    private Operators operator;

    @ManyToOne
    @JoinColumn(name = "route_id", insertable = false, updatable = false)
    private Route route;

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}
