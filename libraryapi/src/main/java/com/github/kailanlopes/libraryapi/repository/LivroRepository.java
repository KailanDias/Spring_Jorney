package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.model.Autor;
import com.github.kailanlopes.libraryapi.model.GeneroLivro;
import com.github.kailanlopes.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //QUERY METHOD
    List<Livro> findByAutor(Autor autor);

    //Cria uma classe abstrata. Lista do tipo livro recebendo o titulo do livro
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    //List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //JPQL ->Referencia as Entidades e as Propiedades
    @Query(" select l from Livro as l order by l.titulo ")
    List<Livro> listarTodosOrdenadosPorTitulo();

    @Query("select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    //Named Parameters -> paramentros nomeados
    @Query("select l from Livro l where l.genero = :genero ") //Assim é usado para parametrizar o @Query
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);

    //Positional Parameters -> paramentros Possicionais
    @Query("select l from Livro l where l.genero = ?1") //Assim é usado para parametrizar o @Query
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro);

    @Modifying //Esta dizendo que vai fazer akguma modificacao de registros
    @Transactional //Sempre que for fazer uma opercao de UPDATE, DELETE, etc... É preciso usar
    @Query("delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro generoLivro);

    @Modifying
    @Transactional
    @Query(" update Livro set datapublicacao = ?1")
    void updateByDatapublicacao(LocalDate novaData);


}
