package com.github.KailanDias.arquiteruraspring;

import com.github.KailanDias.arquiteruraspring.todos.TodoEntity;
import com.github.KailanDias.arquiteruraspring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BeanGeranciado {

    @Autowired
    private TodoValidator validator;

    @Autowired
    public BeanGeranciado(TodoValidator validator) {
        this.validator = validator;
    }

    public void utilizar(){
        var todo = new TodoEntity();
        validator.validar(todo);
    }

    @Autowired
    public void setValidator(TodoValidator validator) {
        this.validator = validator;
    }
}
