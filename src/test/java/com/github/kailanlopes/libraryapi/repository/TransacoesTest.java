package com.github.kailanlopes.libraryapi.repository;

import com.github.kailanlopes.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {




    @Autowired
    TransacaoService transacaoService;

//    @Test
//    void transacaoSimple(){
//        transacaoService.executar();
//    }


    @Test
    void transacaoEstadoManeged(){
        transacaoService.atualizarSemAtualizar();
    }


}
