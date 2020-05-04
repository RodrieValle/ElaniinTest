package com.elaniin.products.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaniin.products.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

	Usuario findOneByEmail(String email);
	
	Usuario findOneByUsername(String username);
}
