package com.elaniin.products.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_generator")
	@SequenceGenerator(name = "producto_generator", sequenceName = "producto_seq")
	@Column(name = "id_producto", updatable = false, nullable = false)
	private Integer idProducto;

	@Column(name = "sku", nullable = true)
	private String sku;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@Column(name = "precio", nullable = false)
	private BigDecimal precio;
	
	@Column(name = "descripcion", nullable = true)
	private String descripcion;
	
	@Column(name = "imagen", nullable = true)
	private String imagen;

	

}
