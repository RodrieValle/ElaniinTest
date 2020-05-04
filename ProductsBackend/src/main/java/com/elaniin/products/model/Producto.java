package com.elaniin.products.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_generator")
	@SequenceGenerator(name = "producto_generator", sequenceName = "producto_seq")
	@Column(name = "id_producto", updatable = false, nullable = false)
	private Integer idProducto;

	@Size(max = 255, message = "SKU no debe tener más de 255 caracteres")
	@Column(name = "sku", nullable = true)
	private String sku;
	
	@Size(max = 255, message = "Nombre no debe tener más de 255 caracteres")
	@NotBlank(message = "Nombre es un valor requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Digits(message = "Cantidad debe ser un valor numérico entero", fraction = 0, integer = 10)
	@NotNull(message = "Cantidad es un valor requerido")
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@Digits(message = "Precio debe ser un valor numérico con dos decimales", fraction = 2, integer = 19)
	@NotNull(message = "Precio es un valor requerido")
	@Column(name = "precio", nullable = false)
	private BigDecimal precio;
	
	@Size(max = 255, message = "Descripción no debe tener más de 255 caracteres")
	@Column(name = "descripcion", nullable = true)
	private String descripcion;
	
	@Size(max = 255, message = "Imagen no debe tener más de 255 caracteres")
	@Column(name = "imagen", nullable = true)
	private String imagen;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	

}
