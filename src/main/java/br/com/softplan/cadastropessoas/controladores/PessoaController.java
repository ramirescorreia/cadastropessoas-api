package br.com.softplan.cadastropessoas.controladores;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.cadastropessoas.entidades.Pessoa;
import br.com.softplan.cadastropessoas.repositorios.PessoaRepositorio;
import br.com.softplan.cadastropessoas.servicos.PessoaServiceIpml;


@RestController
@RequestMapping(path = "/pessoa", consumes = "application/json", produces = "application/json")
public class PessoaController {

	private PessoaRepositorio pessoaRepositorio;
	private PessoaServiceIpml pessoaServico;
	
	@Autowired
	public PessoaController(PessoaServiceIpml pessoaServico, PessoaRepositorio pessoaRepositorio) {
		this.pessoaServico = pessoaServico;
		this.pessoaRepositorio = pessoaRepositorio;
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
		pessoaRepositorio.save(pessoa);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{cpf}", method= RequestMethod.GET)
	public ResponseEntity<Pessoa> buscarPessoaPeloCpf(@PathVariable("cpf") String cpf){
		Pessoa pessoa = pessoaRepositorio.findPessoaByCpf(cpf);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletar/{cpf}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable("cpf") String cpf) {
		Pessoa pessoa = pessoaRepositorio.findPessoaByCpf(cpf);
		if(Objects.nonNull(pessoa)) {
			pessoaRepositorio.delete(pessoa);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Pessoa>> consultarTodasPessoas() {
		List<Pessoa> pessoas = pessoaServico.findAll();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
}
