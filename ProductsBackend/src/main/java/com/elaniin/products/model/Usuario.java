package com.elaniin.products.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
	@SequenceGenerator(name = "usuario_generator", sequenceName = "usuario_seq")
	@Column(name = "id_usuario", updatable = false, nullable = false)
	private Integer idUsuario;

	@Size(max = 255, message = "Nombre no debe tener más de 255 caracteres")
	@NotBlank(message = "Nombre es un valor requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Digits(message = "Teléfono debe ser un valor numérico", fraction = 0, integer = 255)
	@Size(max = 255, message = "Teléfono no debe tener más de 255 dígitos")
	@Column(name = "telefono", nullable = true)
	private String telefono;
	
	@Size(max = 255, message = "Username no debe tener más de 255 caracteres")
	@NotBlank(message = "Username es un valor requerido")
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	@Column(name = "fecha_nacimiento", nullable = true)
	private LocalDateTime fechaNacimiento;

	@Email(message = "El email ingresado no es válido")
	@Size(max = 255, message = "Email no debe tener más de 255 caracteres")
	@NotBlank(message = "Email es un valor requerido")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Size(max = 100, message = "Password no debe tener más de 100 caracteres")
	@NotBlank(message = "Password es un valor requerido")
	@Column(name = "password", nullable = false)
	private String password;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
