package com.camel.core.bean;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.camel.core.service.LinguagemService;

@Controller
public class LinguagensMountBean {

	@Autowired
	private LinguagemService service;
	
	public void montaDTOLinguagens(Exchange exchange) {
		//exchange.getOut().setBody(service.getAllLinguagens());
		exchange.getOut().setBody(service.getListEmpty());
		exchange.setProperty("lista", service.getListEmpty());
	}
	
}
