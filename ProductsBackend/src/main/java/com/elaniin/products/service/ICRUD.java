package com.elaniin.products.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T obj) throws Exception;
	T modificar(T obj);
	List<T> listar();
	T leerPorId(Integer id);
	boolean eliminar(Integer id);
}
