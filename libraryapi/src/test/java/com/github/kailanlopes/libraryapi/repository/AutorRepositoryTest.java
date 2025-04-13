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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvaTest(){
        Autor autor = new Autor();
        autor.setNome("jose");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1955, 1,31));


        var autorSalvo = autorRepository.save(autor);
        System.out.println("autor salvo: " + autorSalvo );
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("805b2cbb-bc20-4535-9cf6-f3fa0e9987d2");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println( possivelAutor.get());

            autorEncontrado.setNome("Maria");
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());

    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("af5930ea-bbdf-496f-9df0-2b99451a1594");
        autorRepository.deleteById(id);
    }

    @Test
    void alvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("jose");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1955, 1,31));

        Livro livro = new Livro();
        livro.setIsbn("9871-6662");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("A casa assombrada");
        livro.setDatapublicacao(LocalDate.of(2000,8,5));

        Livro livro2 = new Livro();
        livro.setIsbn("8943-1472");
        livro.setPreco(BigDecimal.valueOf(650));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("A casa assombrada prt2");
        livro.setDatapublicacao(LocalDate.of(2000,8,5));

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(List.of(livro,livro2));

    }

    @Test
    @Transactional
    void listarLivrosAutor(){
        var id = UUID.fromString("e32858e0-f610-4bf4-8530-e94801c0975c");
        var autor = autorRepository.findById(id).get();
        autor.getLivros().forEach(System.out::println);
    }

}
