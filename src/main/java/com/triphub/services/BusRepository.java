package com.triphub.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.triphub.model.Bus;

//Update it to
public interface BusRepository extends JpaRepository<Bus, String> {

}