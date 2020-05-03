package com.elaniin.products.exception;

public class ModelNotFoundException extends RuntimeException {

	public ModelNotFoundException(String mensaje) {
		super(mensaje);
	}
}
