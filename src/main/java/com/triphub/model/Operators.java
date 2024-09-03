package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "operators")
public class Operators {

    @Id
    @Column(name = "operator_id", nullable = false, length = 244)
    private String operatorId;

    @Column(name = "operator_name", length = 244)
    private String operatorName;

    @Column(name = "ph_no")
    private Integer phNo;

    @Column(name = "bus_count")
    private Integer busCount;

    @Column(name = "address", length = 244)
    private String address;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getPhNo() {
		return phNo;
	}

	public void setPhNo(Integer phNo) {
		this.phNo = phNo;
	}

	public Integer getBusCount() {
		return busCount;
	}

	public void setBusCount(Integer busCount) {
		this.busCount = busCount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    // Getters and Setters
    // ...
    
}
