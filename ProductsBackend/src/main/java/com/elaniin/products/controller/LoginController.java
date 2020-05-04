package com.elaniin.products.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elaniin.products.model.ResetToken;
import com.elaniin.products.model.Usuario;
import com.elaniin.products.service.ILoginService;
import com.elaniin.products.service.IResetTokenService;
import com.elaniin.products.util.EmailUtil;
import com.elaniin.products.util.Mail;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ILoginService service;	
	
	@Autowired
	private IResetTokenService tokenService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Value("${time_reset_password}")
	private int passwordResetTime;
	
	@Value("${tomcat_server_ip}")
	private String tomcatServerIp;
	
	@PostMapping(value = "/enviarCorreo", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Integer> enviarCorreo(@RequestBody String correo) {
		int rpta = 0;
		try {
			Usuario us = service.verificarNombreUsuario(correo);
			if (us != null && us.getIdUsuario() > 0) {
				System.out.print("entramos a envio de correo");
				ResetToken token = new ResetToken();
				token.setToken(UUID.randomUUID().toString());
				token.setUser(us);
				token.setExpiracion(passwordResetTime);
				tokenService.guardar(token);
				
				Mail mail = new Mail();
				mail.setFrom("email.prueba.demo@gmail.com");
				mail.setTo(us.getEmail());
				mail.setSubject("RESTABLECER CONTRASEÑA - Elannin Products");
				
				Map<String, Object> model = new HashMap<>();
				String url = "http://" + tomcatServerIp + "/login/restablecer/verificar/" + token.getToken();
				model.put("user", token.getUser().getNombre());
				model.put("resetUrl", url);
				mail.setModel(model);
				emailUtil.enviarMail(mail);
				rpta = 1;
				System.out.print("correo enviado");
			}
		} catch (Exception e) {
			System.out.print(e);
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
		
	@GetMapping(value = "/restablecer/verificar/{token}")
	public ResponseEntity<String> restablecerClave(@PathVariable("token") String token) {
		int rpta = 0;
		String mensaje = "";
		try {
			if (token != null && !token.isEmpty()) {
				ResetToken rt = tokenService.findByToken(token);
				if (rt != null && rt.getIdResetToken() > 0) {
					if (!rt.estaExpirado()) {
						rpta = 1;
					}
				}
				if(rpta == 1) {
					mensaje = "Token validado correctamente";
				}
				else {
					mensaje = "Token inválido o expirado";
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token, @RequestBody String clave) {
		int rpta = 0;
		try {
			ResetToken rt = tokenService.findByToken(token);
			String claveHash = bcrypt.encode(clave);
			rpta = service.cambiarClave(claveHash, rt.getUser().getUsername());
			tokenService.eliminar(rt);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
}
