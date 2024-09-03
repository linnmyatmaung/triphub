package com.triphub.model;
import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seats {

    @Id
    @Column(name = "seat_id", nullable = false, length = 244)
    private String seatId;

    @Column(name = "bus_id", length = 244)
    private String busId;

    @Column(name = "seatnumber", length = 244)
    private String seatnumber;

    @Column(name = "seat_price")
    private Integer seatPrice;

    @Column(name = "status", length = 255)
    private String status;

    @ManyToOne
    @JoinColumn(name = "bus_id", insertable = false, updatable = false)
    private Bus bus;

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getSeatnumber() {
		return seatnumber;
	}

	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}

	public Integer getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Integer seatPrice) {
		this.seatPrice = seatPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
