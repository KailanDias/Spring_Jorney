package com.github.KailanDias.arquiteruraspring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviarMail(String mensagem){
        System.out.println(" Enviando mensagem: " + mensagem);
    }





}
