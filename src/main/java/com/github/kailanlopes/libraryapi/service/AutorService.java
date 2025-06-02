package com.github.kailanlopes.libraryapi.service;

import com.github.kailanlopes.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.repository.AutorRepository;
import com.github.kailanlopes.libraryapi.repository.LivroRepository;
import com.github.kailanlopes.libraryapi.validator.AutorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final AutorValidator autorValidator;

    public AutorService (AutorRepository autorRepository, AutorValidator autorValidator, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
        this.livroRepository = livroRepository;
    }

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar é nescessario que o autor ja esteja na base");
        }
        autorValidator.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        if (possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é permitido, Autor possui livros cadastrados");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisar(String nome, String nacionalidae){

        if (nome != null && nacionalidae != null){ // se passou as duas
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidae);
        }
        if (nome != null){ // se passou somente o nome
            return autorRepository.findByNome(nome);
        }
        if (nacionalidae != null){ // se passou somente a nacionalidade
            return autorRepository.findByNacionalidade(nacionalidae);
        }
        return autorRepository.findAll(); // se nao passou nada
    }

    public Boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
