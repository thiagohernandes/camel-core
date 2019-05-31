package com.camel.core.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "linguagens") 
@XmlAccessorType(XmlAccessType.FIELD)
public class LinguagensDTOXML {

	List<LinguagemDTOXML> linguagens = new ArrayList<>();

	public List<LinguagemDTOXML> getLinguagens() {
		return linguagens;
	}

	public void setLinguagens(List<LinguagemDTOXML> linguagens) {
		this.linguagens = linguagens;
	}
	
	
}
