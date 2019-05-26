package com.camel.core;

import com.camel.core.bean.MoveFileBean;
import com.camel.core.util.UtilRunCamel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/*
* Classe Main
* @author Thiago Hernandes de Souza
* @since 26-05-2019
* */

@SpringBootApplication
public class CamelCoreApplication {

	@Autowired
	UtilRunCamel util;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CamelCoreApplication.class, args);
	}

	@PostConstruct
	private void init() throws Exception {
		List<Object> listaRoutes = new ArrayList<>();
		listaRoutes.add(new MoveFileBean());
		util.runContextCamel(listaRoutes,2000);
	}

}
