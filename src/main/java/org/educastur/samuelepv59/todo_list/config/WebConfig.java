package org.educastur.samuelepv59.todo_list.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de la Web MVC.
 * Su función principal aquí es gestionar el CORS (Cross-Origin Resource Sharing).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Este método define quién tiene permiso para "hablar" con nuestro Backend.
     * Por seguridad, los navegadores bloquean peticiones que vienen de dominios distintos.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Indicamos que estas reglas se aplican a TODAS las rutas de nuestra API
        registry.addMapping("/**")
                // Definimos el origen permitido (en este caso, donde suele correr el Frontend en desarrollo)
                .allowedOrigins("http://localhost:3000")
                // Especificamos qué verbos HTTP tiene permiso para usar el cliente
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Tiempo que el navegador puede cachear esta configuración (en segundos)
                .maxAge(3600);
    }
}