package com.triphub.services;
import org.springframework.data.jpa.repository.JpaRepository;
import com.triphub.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
