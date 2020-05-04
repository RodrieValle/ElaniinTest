package com.elaniin.products.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaniin.products.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Integer> {
	
	//from ResetToken where token = :?
	ResetToken findByToken(String token);

}
