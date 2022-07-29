package com.io.github.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.github.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
