package br.com.softplan.cadastropessoas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.cadastropessoas.entidades.Usuario;

@Repository
public interface UsuarioRespositorio extends JpaRepository<Usuario, Long>{
	Usuario findByNomeUsuario(String nomeUsuario);
}
