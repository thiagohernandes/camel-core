package com.camel.core.util;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import com.camel.core.predicate.ErrorField;


/*
 * Util App
 * @author Thiago Hernandes de Souza
 * @since 26-05-2019
 * */

@Component
public class UtilRunCamel {

    public void runContextCamel(List<Object> listRoutes, int timeMilisegundos) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
        listRoutes.stream().forEach(i -> {
            try {
                ctx.addRoutes((RoutesBuilder) i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ctx.start();
        Thread.sleep(timeMilisegundos);
        ctx.stop();
    }
    
    public String converErrorListToString(List<ErrorField> errorList) {
    	StringBuilder erros = new StringBuilder();
    	errorList.stream().forEach(i -> {
    		erros.append(i.getMensagem());
    	});
    	return erros.toString();    	
    }
    
}
