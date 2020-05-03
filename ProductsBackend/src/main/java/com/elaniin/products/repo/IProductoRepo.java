package com.elaniin.products.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaniin.products.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer> {


}
