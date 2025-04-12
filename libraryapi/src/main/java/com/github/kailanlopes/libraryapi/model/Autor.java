package com.github.kailanlopes.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity //Obrigatorio para dizer que esta se referenciando a uma tabela do banco de dados
@Table(name="autor", schema = "public")
@Getter // Utilizando o lombok cria em tempo de execusao os getters e setters
@Setter
@ToString
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID) //Gera automaticamente o ID
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacioanlidade")
    private  String nacionalidade;

    //@OneToMany (mappedBy = "autor")//Mapeamento um autor para muitos livros
    @Transient
    private List<Livro> livros; //lista de livros do autor

    @Deprecated
    public Autor() {
        //Para uso do framework
    }


}
