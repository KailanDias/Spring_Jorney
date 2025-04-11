package com.github.kailanlopes.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data //Annotation do lombok para criar os getters e setters, entre ourtas...
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 30, nullable = false)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "data_publicacao" , nullable = false)
    private LocalDate datapublicacao;

    @Enumerated(EnumType.STRING) //Referencia a Classe Enum
    @Column(name = "genero", length = 30,nullable = false )
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2, nullable = false)
    private Double preco;

    @ManyToOne() // relacao muitos pra um com autor
    @JoinColumn(name = "id_autor")//Mapeamento da coluna chave estrangeira
    private Autor autor;


//    id UUID NOT NULL PRIMARY KEY,
//    isbn VARCHAR(30) NOT NULL,
//    titulo VARCHAR(100) NOT NULL,
//    data_publicacao DATE NOT NULL,
//    genero VARCHAR(30) NOT NULL,
//    preco numeric(18,2) NOT NULL,
//    id_autor UUID NOT NULL REFERENCES autor(id),
}
