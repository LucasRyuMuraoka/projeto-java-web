package com.poo.projetoweb.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poo.projetoweb.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByNome(String nome);
	List<User> findByNomeContaining(String nome);
}
