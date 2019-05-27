package com.camel.core.bean;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.camel.core.service.LinguagemService;

/* 
 * Bean Predicate Linguagem
 * @author Thiago Hernandes de Souza
 * @since 27-05-2019 
 **/

@Controller
public class LinguagensMountBean {

	@Autowired
	private LinguagemService service;
	
	public void montaDTOLinguagens(Exchange exchange) {
		exchange.getOut().setBody(service.getAllLinguagens());
		//exchange.getOut().setBody(service.getListEmpty());
		exchange.setProperty("lista", service.getAllLinguagens());
	}
	
}
