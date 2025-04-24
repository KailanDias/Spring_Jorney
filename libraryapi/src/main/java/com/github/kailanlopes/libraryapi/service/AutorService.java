package com.github.kailanlopes.libraryapi.service;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService (AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }
}
