package com.elaniin.products.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elaniin.products.model.Usuario;
import com.elaniin.products.repo.IUsuarioRepo;
import com.elaniin.products.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService{

	@Autowired	
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public Usuario registrar(Usuario obj) throws Exception {
		Usuario usuarioExistente = null;
		//Se valida que el email ingresado no exista
		usuarioExistente = repo.findOneByEmail(obj.getEmail());
		if(usuarioExistente != null) {
			throw new Exception("Ya existe usuario con ese email");
		}
		//Se valida que el username ingresado no exista
		usuarioExistente = repo.findOneByUsername(obj.getUsername());
		if(usuarioExistente != null) {
			throw new Exception("Ya existe usuario con ese nombre de usuario");
		}
		//Se realiza encriptación de contraseña
		obj.setPassword(bcrypt.encode(obj.getPassword()));
		return repo.save(obj);
	}

	@Override
	public Usuario modificar(Usuario obj) {
		obj.setPassword(bcrypt.encode(obj.getPassword()));
		return repo.save(obj);
	}

	@Override
	public List<Usuario> listar() {
		return repo.findAll();
	}

	@Override
	public Usuario leerPorId(Integer id) {
		Optional<Usuario> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Usuario();
	}

	@Override
	public boolean eliminar(Integer id) {		
		repo.deleteById(id);
		return true;
	}
	
	@Override
	public Page<Usuario> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Para el inicio de sesión se usa el email, por lo que la búsqueda entiende el email como username
		Usuario usuario = repo.findOneByEmail(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		UserDetails ud = new User(usuario.getEmail(), usuario.getPassword(), roles);
		return ud;
	}

}
