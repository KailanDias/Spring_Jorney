package com.github.kailanlopes.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity //Obrigatorio para dizer que esta se referenciando a uma tabela do banco de dados
@Table(name="autor", schema = "public")
@Getter // Utilizando o lombok cria em tempo de execusao os getters e setters
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID) //Gera automaticamente o ID
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade")
    private  String nacionalidade;

    @OneToMany (mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //Mapeamento um autor para muitos livros
    private List<Livro> livros; //lista de livros do autor

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private UUID idUsuario;
}
