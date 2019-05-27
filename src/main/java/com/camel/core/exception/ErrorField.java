package com.camel.core.exception;

public class ErrorField {
	
	private String mensagem;
	
	public ErrorField() {
		
	}
	
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
