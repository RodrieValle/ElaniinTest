package com.elaniin.products.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elaniin.products.model.Producto;

public interface IProductoService extends ICRUD<Producto>{

	Page<Producto> listarPageable(Pageable pageable);

}
