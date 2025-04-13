package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //QUERY METHOD
    List<Livro> findByAutor(Autor autor);

    //Cria uma classe abstrata. Lista do tipo livro recebendo o titulo do livro
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    

}
