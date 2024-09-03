package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
@IdClass(AdminId.class)
public class Admin {

    @Id
    @Column(name = "admin_id", nullable = false, length = 244)
    private String adminId;

    @Id
    @Column(name = "operator_id", nullable = false, length = 244)
    private String operatorId;

    @Column(name = "password", length = 244)
    private String password;

    @Column(name = "full_name", length = 244)
    private String fullName;

    @Column(name = "email", length = 244)
    private String email;

    @Column(name = "phone_no")
    private Integer phoneNo;

    @ManyToOne
    @JoinColumn(name = "operator_id", insertable = false, updatable = false)
    private Operators operator;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

    // Getters and Setters
    // ...
    
}



