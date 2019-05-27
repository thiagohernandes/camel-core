package com.camel.core.validation;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Controller;

import com.camel.core.domain.Linguagem;

@Controller
public class ValidationGenericController {
	
	public void validarListaLinguagem(Exchange exchange) {
		List<Linguagem> property = (List<Linguagem>) exchange.getProperty("lista");
		List<Linguagem> lista = property;
		if (lista.isEmpty() || lista.size() == 0) {
			exchange.setProperty("mensagem", "SIM");
		} else {
			exchange.setProperty("mensagem", "NÃO");
		}
		
	}

}
