package com.io.github.model.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.io.github.model.entity.Cliente;
import com.io.github.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private final ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("{id}")
	public Cliente find(@PathVariable Integer id) {
		return clienteRepository
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));				
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Delete(@PathVariable Integer id) {
		clienteRepository	
				.findById(id)
				.map( cliente -> {
					clienteRepository.delete(cliente);
					return Void.TYPE;
				})
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));				
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		clienteRepository
				.findById(id)
				.map( cliente -> {
					cliente.setNome(clienteAtualizado.getNome());
					cliente.setCpf(clienteAtualizado.getCpf());
					return clienteRepository.save(cliente);
				})
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));				
	}
}
