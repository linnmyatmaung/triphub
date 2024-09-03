package com.triphub.model;

import java.io.Serializable;

public class AdminId implements Serializable {
    private String adminId;
    private String operatorId;

    // Default constructor
    public AdminId() {}

    // Constructor
    public AdminId(String adminId, String operatorId) {
        this.adminId = adminId;
        this.operatorId = operatorId;
    }

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

    // Getters and Setters
    // hashCode and equals methods
    // ...
}