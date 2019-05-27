package com.camel.core.predicate;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

import com.camel.core.domain.Linguagem;

/* 
 * Predicate de validação específico de linguagem
 * @author Thiago Hernandes de Souza
 * @since 27-05-2019 
 **/

public class LinguagemPredicate implements Predicate {

	List<ErrorField> errorList = new ArrayList<>();
	
	@Override
	public boolean matches(Exchange exchange) {
		errorList.clear();
		@SuppressWarnings("unchecked")
		List<Linguagem> lista = (List<Linguagem>) exchange.getIn().getBody();
		if(lista.size() == 0) {
			errorList.add(new ErrorField("lista vazia"));
			exchange.setProperty("Erros", errorList);
			return false;
		}	
		return true;
	}

}
