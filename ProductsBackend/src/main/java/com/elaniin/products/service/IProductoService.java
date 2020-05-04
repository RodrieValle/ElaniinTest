package com.elaniin.products.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elaniin.products.dto.FiltroConsultaDTO;
import com.elaniin.products.model.Producto;

public interface IProductoService extends ICRUD<Producto>{

	Page<Producto> listarPageable(Pageable pageable);

	List<Producto> buscar(FiltroConsultaDTO filtro);
}
