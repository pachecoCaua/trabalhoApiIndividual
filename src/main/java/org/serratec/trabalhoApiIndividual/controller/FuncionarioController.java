package org.serratec.trabalhoApiIndividual.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.trabalhoApiIndividual.domain.Endereco;
import org.serratec.trabalhoApiIndividual.domain.Funcionario;
import org.serratec.trabalhoApiIndividual.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public List<Funcionario> listarFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long id) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
		if (!funcionarioOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(funcionarioOpt.get());
	}

	@PostMapping
	public Funcionario criarFuncionario(@RequestBody @Valid Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
			@RequestBody Funcionario funcionario) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
		if (!funcionarioOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Funcionario funcionarioAtual = funcionarioOpt.get();

		if (funcionario.getNome() != null) {
			funcionarioAtual.setNome(funcionario.getNome());
		}

		if (funcionario.getEndereco() != null) {
			if (funcionarioAtual.getEndereco() == null) {
				funcionarioAtual.setEndereco(new Endereco());
			}
			Endereco enderecoAtual = funcionarioAtual.getEndereco();
			Endereco enderecoEnviado = funcionario.getEndereco();

			if (enderecoEnviado.getRua() != null) {
				enderecoAtual.setRua(enderecoEnviado.getRua());
			}
			if (enderecoEnviado.getCidade() != null) {
				enderecoAtual.setCidade(enderecoEnviado.getCidade());
			}
			if (enderecoEnviado.getEstado() != null) {
				enderecoAtual.setEstado(enderecoEnviado.getEstado());
			}
			if (enderecoEnviado.getCep() != null) {
				enderecoAtual.setCep(enderecoEnviado.getCep());
			}
		}

		Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioAtual);
		return ResponseEntity.ok(funcionarioAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}