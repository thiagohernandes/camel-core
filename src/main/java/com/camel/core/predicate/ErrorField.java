package com.camel.core.predicate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/* 
 * DTO validador field
 * @author Thiago Hernandes de Souza
 * @since 27-05-2019 
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"mensagem"})
public class ErrorField {
	
	@JsonProperty(value = "mensagem")
	private String mensagem;
	
	public ErrorField(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
