package com.fabian.PlaylistManagerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PlaylistManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistManagerApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Permite todas las rutas
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200") // Permite solicitudes de este origen (ajustar si es necesario)
						.allowedMethods("*") // Métodos permitidos
						.allowedHeaders("*") // Todos los encabezados permitidos
						.allowCredentials(true); // Permitir credenciales (como cookies)
			}
		};
	}
}
