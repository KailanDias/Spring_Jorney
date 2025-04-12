package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

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
        var id = UUID.fromString("2042144c-8c95-4905-b340-7be2b26bb835");
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
        var id = UUID.fromString("b6d2f9c4-0bee-417d-b6e2-74a06fdf08f6");
        autorRepository.deleteById(id);
    }


}
