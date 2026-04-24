package org.educastur.samuelepv59.todo_list.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Permite usar @PreAuthorize en tus servicios más adelante
@RequiredArgsConstructor
public class SecurityConfig {

    // --- DESCOMENTAR CUANDO IMPLEMENTES LOS MANEJADORES DE ERRORES ---
    // private final CustomAccessDeniedHandler accessDeniedHandler;
    // private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults()) // Permite peticiones desde otros dominios (Frontend)
                .httpBasic(Customizer.withDefaults()) // Login mediante ventana emergente del navegador/Postman

                /* --- DESCOMENTAR ESTO PARA GESTIÓN DE ERRORES PERSONALIZADA ---
                .exceptionHandling(excep -> {
                    excep.accessDeniedHandler(accessDeniedHandler);
                    excep.authenticationEntryPoint(authenticationEntryPoint);
                })
                */

                .authorizeHttpRequests((authz) -> authz
                        // Permitimos acceso a la documentación de la API sin login
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Permitimos que cualquiera pueda registrarse
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // Permitimos las peticiones de comprobación del navegador (CORS)
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()

                        // Cualquier otra petición (tareas, categorías) requiere estar logueado
                        .anyRequest().authenticated()
                );

        // Desactivamos la protección CSRF (común en APIs REST con Tokens/Basic)
        http.csrf(AbstractHttpConfigurer::disable);

        // Necesario para que la consola de H2 se vea correctamente en el navegador
        http.headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}