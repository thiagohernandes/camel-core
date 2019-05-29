package com.camel.core.service;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.camel.core.domain.Linguagem;
import com.camel.core.dto.LinguagemDTOXML;
import com.camel.core.dto.LinguagensDTOXML;

@Service
public class LinguagemService {

    public List<Linguagem> getAllLinguagens() {
        return Arrays.asList(new Linguagem(1, "Java"), new Linguagem(2, "Python"),
                             new Linguagem(3, "Dart"), new Linguagem(4, "Perl"));
    }
    
    public LinguagensDTOXML getAllLinguagensXML() {
    	LinguagensDTOXML linguagens = new LinguagensDTOXML();
    	linguagens.setLinguagens(Arrays.asList(new LinguagemDTOXML(1, "Java"), new LinguagemDTOXML(2, "Python"),
                             new LinguagemDTOXML(3, "Dart"), new LinguagemDTOXML(4, "Perl")));
        return linguagens;
    }
    
    public void XMLToString() throws JAXBException {
    	LinguagensDTOXML lista = getAllLinguagensXML();
    	System.out.println("-------------------- XML String");
    	lista.getLinguagens().stream().forEach(i -> System.out.println(i.getNome()));
    }
    

    public Linguagem getJava(){
        return new Linguagem(1, "Java");
    }
    
    public List<Linguagem> getListEmpty(){
    	return Arrays.asList();
    }
}
