package com.triphub.services;

import com.triphub.model.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedbacks, String> {
	Optional<Feedbacks> findByPassengerId(String passengerId);

}
