package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "emp_id", nullable = false, length = 244)
    private String empId;

    @Column(name = "emp_name", length = 244)
    private String empName;

    @Column(name = "emp_role", length = 244)
    private String empRole;

    @Column(name = "status", length = 244)
    private String status;

    @Column(name = "bus_id", length = 244)
    private String busId;

    @Column(name = "started_date")
    private java.sql.Date startedDate;

    @ManyToOne
    @JoinColumn(name = "bus_id", insertable = false, updatable = false)
    private Bus bus;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
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

	public java.sql.Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(java.sql.Date startedDate) {
		this.startedDate = startedDate;
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
