package com.camel.core.util;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * Util App
 * @author Thiago Hernandes de Souza
 * @since 26-05-2019
 * */

@Component
public class UtilRunCamel {

    public void runContextCamel(List<Object> listRoutes) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
        listRoutes.stream().forEach(i -> {
            try {
                ctx.addRoutes((RoutesBuilder) i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ctx.start();
        Thread.sleep(2000);
        ctx.stop();
    }
}
