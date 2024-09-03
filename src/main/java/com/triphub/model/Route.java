package com.triphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @Column(name = "route_id", nullable = false, length = 244)
    private String routeId;

    @Column(name = "sources", length = 244)
    private String sources;

    @Column(name = "destinations", length = 244)
    private String destinations;

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}

    
}
