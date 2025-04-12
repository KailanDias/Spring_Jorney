package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.model.GeneroLivro;
import com.github.kailanlopes.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("9091-123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDatapublicacao(LocalDate.of(1980,02,02));
        Autor autor = autorRepository.findById(UUID.fromString("d604bfa3-cafe-40b9-9a99-0941bfaaded6")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("9091-123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDatapublicacao(LocalDate.of(1980,02,02));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1955, 1,31));


        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    void atualizarAutorLivroTest(){
        UUID id = UUID.fromString("60fce447-0e98-4451-956a-455b304a4eed");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("2042144c-8c95-4905-b340-7be2b26bb835");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        livroRepository.save(livroParaAtualizar);
    }
    @Test
    void deletar(){
        var id = UUID.fromString("60fce447-0e98-4451-956a-455b304a4eed");
        livroRepository.deleteById(id);
    }



}