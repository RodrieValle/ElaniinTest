package com.elaniin.products.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elaniin.products.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer> {

	@Query("select distinct p from Producto p where LOWER(p.nombre) like %:nombre%")
	List<Producto> buscarPorNombre(@Param("nombre")String nombre);
	
	@Query("select distinct p from Producto p where LOWER(p.sku) like %:sku%")
	List<Producto> buscarPorSku(@Param("sku") String sku);
	
	@Query("select distinct p from Producto p where LOWER(p.nombre) like %:nombre% or LOWER(p.sku) like %:sku%")
	List<Producto> buscarPorNombreYSku(@Param("nombre")String nombre,@Param("sku") String sku);
	
}
