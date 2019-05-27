package com.camel.core.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.camel.core.bean.LinguagensMountBean;
import com.camel.core.domain.Linguagem;
import com.camel.core.validation.ValidationGenericController;

/*
 * Rota REST Linguagem
 * @author Thiago Hernandes de Souza
 * @since 26-05-2019
 * */

@Component
public class LinguagemRoute extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        rest("/api-linguagem")
                    .get("/{nome}")
                    .route().transform().simple("Linguagem -> ${header.nome} ")
                .endRest()
                    .get("/all/{nome}").to("direct:linguagem-teste");
        
        rest("/api-linguagem")
        	.get("/all").to("direct:all-linguagens");

        from("direct:linguagem-teste")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                       final String nome = exchange.getIn().getHeader("nome",String.class);
                        exchange.getOut().setBody(new Linguagem(111,nome));
                        log.info("Body 1 -->>> " + ((Linguagem)exchange.getOut().getBody()).getCodigo());
                        log.info("Body 2 -->>> " + exchange.getIn().getHeader("nome",String.class));
                    }
                });
        
        from("direct:all-linguagens")
        	.bean(LinguagensMountBean.class, "montaDTOLinguagens")
        	.process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                   log.info("---> Log Properties: " + exchange.getProperty("lista").toString());
                }
            })
        	.to("direct:predicate-validation1")
        	.log("${body}");
        
        from("direct:predicate-validation1")
	    	.bean(ValidationGenericController.class, "validarListaLinguagem")
	    	.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	               log.info("---> Log Properties: " + exchange.getProperty("mensagem").toString());
	            }
	        })
	    	.log("${body}");
        
        
    }
}
