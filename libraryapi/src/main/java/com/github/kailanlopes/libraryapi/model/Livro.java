package com.github.kailanlopes.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data //Annotation do lombok para criar os getters e setters, entre ourtas...
@ToString(exclude = "autor")
public class Livro {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 30, nullable = false)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate datapublicacao;

    @Enumerated(EnumType.STRING) //Referencia a Classe Enum
    @Column(name = "genero", length = 30,nullable = false )
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY) // relacao muitos pra um com autor
    @JoinColumn(name = "id_autor")//Mapeamento da coluna chave estrangeira
    private Autor autor;


}
