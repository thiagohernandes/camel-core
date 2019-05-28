package com.camel.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camel.core.domain.Linguagem;
import com.camel.core.service.LinguagemService;

@RestController
@RequestMapping("/api-xml")
public class LinguagemController {

	@Autowired
	LinguagemService service;
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Linguagem> getAllLinguagensXML() {
		return service.getAllLinguagens();
	}
	
}
