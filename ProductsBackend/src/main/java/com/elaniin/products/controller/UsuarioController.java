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

import com.elaniin.products.model.Usuario;
import com.elaniin.products.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP POST, guardar un nuevo usuario
	 */
	@PostMapping
	public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
		Usuario obj = service.registrar(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.CREATED);
	}
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP GET, obtener un listado de usuarios de forma paginada
	 * Para ello se usarla una url del tipo http://localhost/usuarios?page=0&size=5
	 * Page es el número de pagina, iniciando en 0
	 * Size es el tamaño o cantidad de items que tendrá la página
	 */
	@GetMapping
	public ResponseEntity<Page<Usuario>> listarPageable(Pageable pageable) {
		Page<Usuario> usuarios = service.listarPageable(pageable);
		return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP PUT, modificar un usuario existente
	 * Considerar que en el JSON del body debe ir el id de usuario existente
	 */
	@PutMapping
	public ResponseEntity<Usuario> modificar(@Valid @RequestBody Usuario medico) {
		Usuario obj = service.modificar(medico);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}
	
	/*
	 * Funcionalidad que permite, por medio del método HTTP DELETE, eliminar fisicamente un usuario existente
	 * Para usarlo se debe añadir el id del usuario al final de la url del la forma http://localhost/usuarios/4
	 * En el ejemplo, 4 es el id de usuario y se ubica al final luego de una pleca
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception{
		Usuario usuario = service.leerPorId(id);
		if(usuario.getIdUsuario() == null) {
			throw new Exception("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	
	
}
