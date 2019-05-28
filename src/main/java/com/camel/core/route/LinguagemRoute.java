package com.camel.core.route;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.core.bean.LinguagensMountBean;
import com.camel.core.domain.Linguagem;
import com.camel.core.predicate.CustomPredicates;
import com.camel.core.predicate.ErrorField;
import com.camel.core.util.UtilRunCamel;
import com.camel.core.validation.ValidationGenericController;

/*
 * Rota REST Linguagem
 * @author Thiago Hernandes de Souza
 * @since 26-05-2019
 * */

@Component
public class LinguagemRoute extends RouteBuilder {

	@Autowired
	CustomPredicates customPredicates;
	
	@Autowired
	UtilRunCamel util;
	
    @SuppressWarnings("unchecked")
	@Override
    public void configure() throws JAXBException {
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
        
        rest("/api-linguagem")
    		.get("/predicate").to("direct:predicate-ex");
        
        rest("/api-linguagem")
			.get("/jetty").to("direct:jetty-http");
        
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
        
        JAXBContext context = JAXBContext.newInstance(new Class[]{Linguagem.class}); 
        JaxbDataFormat jaxbFormat = new JaxbDataFormat(context); 
        
        from("direct:predicate-ex")
    		.bean(LinguagensMountBean.class, "montaDTOLinguagens")
    		.unmarshal(jaxbFormat)
    		.doTry()
    			.choice()
    				.when(customPredicates.getSimulacaoPredicate())
	    				.process(new Processor() {
	    		            @Override
	    		            public void process(Exchange exchange) throws Exception {
	    		               log.info("---> Log Predicate: " + exchange.getProperty("lista").toString());
	    		            }
	    		        })
	    			.otherwise()
		    			.process(new Processor() {
	    		            @Override
	    		            public void process(Exchange exchange) throws Exception {
	    		            	log.info("---> Lista erros: " + util.converErrorListToString((List<ErrorField>) exchange.getProperty("Erros")));
	    		            }
	    		        })
        		.end()
        	.endDoTry();
        
        from("jetty://http://localhost:8082/teste")
    	.process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
               log.info("---> Log 8082");
            }
        })
    	.log("${body}");
        
        from("direct:jetty-http")
    	.process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
               log.info("---> Log HTTP");
            }
        }).to("jetty://http://localhost:8082/teste?bridgeEndpoint=true")
    	.log("${body}");
        
    }
}
