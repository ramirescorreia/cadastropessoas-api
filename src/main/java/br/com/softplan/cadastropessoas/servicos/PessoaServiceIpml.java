package br.com.softplan.cadastropessoas.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.softplan.cadastropessoas.entidades.Pessoa;
import br.com.softplan.cadastropessoas.repositorios.PessoaRepositorio;

@Service
public class PessoaServiceIpml {
	
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	public PessoaServiceIpml(PessoaRepositorio pessoaRepositorio) {
		this.pessoaRepositorio = pessoaRepositorio;
	}
	
	public List<Pessoa> findAll() {
		Long totalPessoas = pessoaRepositorio.count();
		if(totalPessoas.equals(0L)) {
			return new ArrayList<>();
		}
		Page<Pessoa> page = pessoaRepositorio.findAll(new PageRequest(0, totalPessoas.intValue()));
		return page.getContent();
	}
}
