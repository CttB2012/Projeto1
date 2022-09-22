package com.brq.projeto1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Classe principal para inicialização do projeto
 * @author WGomes
 * @since Release 1.0
 */
@SpringBootApplication
@EnableSwagger2
public class Projeto1Application {

	/**
	 *Método para INICIALIZAR a aplicação
 	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(Projeto1Application.class, args);

	}

}
