package br.com.softplan.cadastropessoas.servicos;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.cadastropessoas.entidades.Usuario;
import br.com.softplan.cadastropessoas.repositorios.UsuarioRespositorio;

@Service
public class UsuarioDetalheServiceImpl implements UserDetailsService{

	private UsuarioRespositorio usuarioRespositorio;
	
	public UsuarioDetalheServiceImpl(UsuarioRespositorio usuarioRespositorio) {
		this.usuarioRespositorio = usuarioRespositorio;
	}
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Usuario usuairo = usuarioRespositorio.findByNomeUsuario(nomeUsuario);
		if(usuairo == null) {
			throw new UsernameNotFoundException(nomeUsuario);
		}
		return new User(usuairo.getNomeUsuario(), usuairo.getPassword(), new ArrayList<>());
	}
}
