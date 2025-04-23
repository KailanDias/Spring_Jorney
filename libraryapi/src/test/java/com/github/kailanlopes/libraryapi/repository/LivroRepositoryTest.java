package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.model.GeneroLivro;
import com.github.kailanlopes.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Test
    @Transactional
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("14785239");
        livro.setPreco(BigDecimal.valueOf(300));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("asaxs");
        livro.setDatapublicacao(LocalDate.of(1980,2,2));
        Autor autor = autorRepository.findById(UUID.fromString("e32858e0-f610-4bf4-8530-e94801c0975c")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("9091-123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("UFO");
        livro.setDatapublicacao(LocalDate.of(1980,02,02));

        Autor autor = new Autor();
        autor.setNome("JOELSON");
        autor.setNacionalidade("Americanbo");
        autor.setDataNascimento(LocalDate.of(2000, 2,25));


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

    @Test
    void buscarlivroTest(){
        UUID id = UUID.fromString("91a0d7f9-73ac-4f73-9345-d4ed5b513d2d"); //Passando o ID do livro que quero consultar
        Livro livro = livroRepository.findById(id).orElse(null); // Pegando o livro
        System.out.print("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.print("Autor: ");//Pega o Titulo do livro
        System.out.println(livro.getAutor().getNome()); // Pega o nome do Autor do livro
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = livroRepository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorISBNTest(){
        List<Livro> lista = livroRepository.findByIsbn("9091-123");
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryTest(){
        var resultado = livroRepository.listarTodosOrdenadosPorTitulo();
        resultado.forEach(System.out::println);

    }

    @Test
    @Transactional
    void listarAutoresDosLivrosComQueryTest() {
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);

    }

    @Test
    void listarPorGenereoQueryParamTest(){
        var resultado = livroRepository.findByGenero(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);

    }

    @Test
    void listarPorGenereoPositionalParamTest(){
        var resultado = livroRepository.findByGeneroPositionalParameters(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);

    }

    @Test
    void deletePorGenereoTest(){
        livroRepository.deleteByGenero(GeneroLivro.FICCAO);
    }

    @Test
    void updateDataPublicacaoLivroTest(){
        livroRepository.updateByDatapublicacao(LocalDate.of(2000,01,01));
    }


}