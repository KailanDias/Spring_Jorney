package com.github.kailanlopes.libraryapi.service;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.model.GeneroLivro;
import com.github.kailanlopes.libraryapi.model.Livro;
import com.github.kailanlopes.libraryapi.repository.AutorRepository;
import com.github.kailanlopes.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;


    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("dd33b0c9-49df-4114-9620-3654994aadb8"))
                .orElse(null);
        livro.setDatapublicacao(LocalDate.now());


    }


    @Transactional
    public void executar(){
        //Salvar o autor
        Autor autor = new Autor();
        autor.setNome("Test Francisco");
        autor.setNacionalidade("Americanbo");
        autor.setDataNascimento(LocalDate.of(2000, 2,25));

        autorRepository.save(autor);

        //salva o livro
        Livro livro = new Livro();
        livro.setIsbn("9091-123");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Livro do francisco");
        livro.setDatapublicacao(LocalDate.of(1980,02,02));
        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Test Francisco")){
            throw new RuntimeException("ROLLBACK!");
        }

    }


}
