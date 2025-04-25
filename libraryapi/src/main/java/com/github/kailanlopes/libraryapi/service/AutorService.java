package com.github.kailanlopes.libraryapi.service;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService (AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public void atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar é nescessario que o autor ja esteja na base");
        }
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
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
}
