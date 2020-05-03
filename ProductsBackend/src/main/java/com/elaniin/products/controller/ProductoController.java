package com.elaniin.products.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elaniin.products.model.Producto;
import com.elaniin.products.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP POST, guardar un nuevo producto
	 */
	@PostMapping
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto producto) {
		Producto obj = service.registrar(producto);
		return new ResponseEntity<Producto>(obj, HttpStatus.CREATED);
	}
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP GET, obtener un listado de productos de forma paginada
	 * Para ello se usarla una url del tipo http://localhost/productos?page=0&size=5
	 * Page es el número de pagina, iniciando en 0
	 * Size es el tamaño o cantidad de items que tendrá la página
	 */
	@GetMapping
	public ResponseEntity<Page<Producto>> listarPageable(Pageable pageable) {
		Page<Producto> productos = service.listarPageable(pageable);
		return new ResponseEntity<Page<Producto>>(productos, HttpStatus.OK);
	}
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP PUT, modificar un producto existente
	 * Considerar que en el JSON del body debe ir el id de producto existente
	 */
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto medico) {
		Producto obj = service.modificar(medico);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP DELETE, eliminar fisicamente un producto existente
	 * Para usarlo se debe añadir el id del producto al final de la url del la forma http://localhost/productos/4
	 * En el ejemplo, 4 es el id de producto y se ubica al final luego de una pleca
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception{
		Producto producto = service.leerPorId(id);
		if(producto.getIdProducto() == null) {
			throw new Exception("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	
	
}
