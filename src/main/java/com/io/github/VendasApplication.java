package com.io.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {

	
// Cria uma cliente utilizando o Builder e salva no banco		
//	@Bean
//	public CommandLineRunner run(@Autowired ClienteRepository clienteRepository) {
//		return args ->{
//			Cliente clientes = Cliente.builder().cpf("00000000000").nome("Fulano").build();
//			clienteRepository.save(clientes);
//		};
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
