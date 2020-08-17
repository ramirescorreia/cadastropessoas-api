package br.com.softplan.cadastropessoas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.cadastropessoas.entidades.Usuario;
import br.com.softplan.cadastropessoas.repositorios.UsuarioRespositorio;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioRespositorio usuarioRespositorio;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UsuarioController(UsuarioRespositorio usuarioRespositorio, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usuarioRespositorio = usuarioRespositorio;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> signUp(@RequestBody Usuario usuario) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuarioRespositorio.save(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
}
