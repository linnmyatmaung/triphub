package com.triphub.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.triphub.model.Passenger;

//Update it to
public interface PassengerRepository extends JpaRepository<Passenger, String> {

}