package com.poo.projetoweb.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poo.projetoweb.domain.model.User;
import com.poo.projetoweb.domain.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> listar() {
		return userRepository.findAll();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> buscar(@PathVariable Long userId) {
		Optional<User> usuario = userRepository.findById(userId);

		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User adicionar(@RequestBody User usuario) {
		return userRepository.save(usuario);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> atualizar(@PathVariable Long userId, @RequestBody User usuario) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		usuario.setId(userId);
		usuario = userRepository.save(usuario);

		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> remover(@PathVariable Long userId) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		userRepository.deleteById(userId);
		return ResponseEntity.noContent().build();
	}

}
