package com.elaniin.products.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elaniin.products.dto.FiltroConsultaDTO;
import com.elaniin.products.model.Producto;
import com.elaniin.products.repo.IProductoRepo;
import com.elaniin.products.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired	
	private IProductoRepo repo;
	
	@Override
	public Producto registrar(Producto obj) {
		return repo.save(obj);
	}

	@Override
	public Producto modificar(Producto obj) {		
		return repo.save(obj);
	}

	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}
	
	@Override
	public Page<Producto> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Producto leerPorId(Integer id) {
		Optional<Producto> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Producto();
	}

	@Override
	public boolean eliminar(Integer id) {		
		repo.deleteById(id);
		return true;
	}

	@Override
	public List<Producto> buscar(FiltroConsultaDTO filtro) {	
		if(filtro.getNombre() != null) {
			if(filtro.getSku() != null) {
				return repo.buscarPorNombreYSku(filtro.getNombre().toLowerCase(), filtro.getSku().toLowerCase());
			}
			else {
				return repo.buscarPorNombre(filtro.getNombre().toLowerCase());
			}
		}
		else {
			return repo.buscarPorSku(filtro.getSku().toLowerCase());
		}
	}

}
