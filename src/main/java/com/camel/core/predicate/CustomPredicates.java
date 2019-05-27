package com.camel.core.predicate;

import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.springframework.stereotype.Component;

/* 
 * Predicates de validação - aqui fica todos os validadores
 * @author Thiago Hernandes de Souza
 * @since 27-05-2019 
 **/

@Component
public class CustomPredicates {

	public Predicate getSimulacaoPredicate() {
		Predicate validacao = new LinguagemPredicate();
		return PredicateBuilder.and(validacao);
	}
	
}
