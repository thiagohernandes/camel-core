package com.camel.core.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class LinguagemDTOXML {

    private int codigo;
    private String nome;

    public LinguagemDTOXML(){
    }

    public LinguagemDTOXML(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
