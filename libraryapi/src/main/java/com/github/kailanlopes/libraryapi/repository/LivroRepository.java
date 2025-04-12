package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {



}
