package com.camel.core.service;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.JSONObject;
import org.json.XML;
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
    
    public String XMLToString(LinguagensDTOXML xml) throws JAXBException {
    	JAXBContext jc = JAXBContext.newInstance(LinguagensDTOXML.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(xml, sw);
       return sw.toString();
    }
    
    public void XMLToJSON(String dadosXML) throws Exception {
    	JSONObject parser = XML.toJSONObject(dadosXML.toString());
        System.out.println(parser.toString());
    }    

    public Linguagem getJava(){
        return new Linguagem(1, "Java");
    }
    
    public List<Linguagem> getListEmpty(){
    	return Arrays.asList();
    }
}
