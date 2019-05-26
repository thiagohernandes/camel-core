package com.camel.core.bean;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/*
 * Bean Move File
 * @author Thiago Hernandes de Souza
 * @since 26-05-2019
 * */

@Component
public class MoveFileBean extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://shared/inshare?noop=true&delete=false")
                .to("file://shared/outshare");
    }
}
