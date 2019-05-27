package com.camel.core.service;

import com.camel.core.domain.Linguagem;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LinguagemService {

    public List<Linguagem> getAllLinguagens() {
        return Arrays.asList(new Linguagem(1, "Java"), new Linguagem(2, "Python"),
                             new Linguagem(3, "Dart"), new Linguagem(4, "Perl"));
    }

    public Linguagem getJava(){
        return new Linguagem(1, "Java");
    }
    
    public List<Linguagem> getListEmpty(){
    	return Arrays.asList();
    }
}
