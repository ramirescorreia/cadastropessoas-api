package br.com.softplan.cadastropessoas.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.cadastropessoas.entidades.Pessoa;

@Repository
public interface PessoaRepositorio extends PagingAndSortingRepository<Pessoa, Long>{
	Pessoa findPessoaByCpf(String cpf);
}
