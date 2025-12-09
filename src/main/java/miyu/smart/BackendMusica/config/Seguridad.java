package miyu.smart.BackendMusica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer; // Importante
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration; // Importante
import org.springframework.web.cors.CorsConfigurationSource; // Importante
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // Importante

import java.util.List;

@Configuration
@EnableWebSecurity
public class Seguridad {

    @Bean
    public SecurityFilterChain fitroSeguridad(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) //AQUÍ ACTIVAMOS LA CONFIGURACIÓN CORS
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitimos el puerto de Vite (Vue) y también tu dominio de producción por si acaso
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "https://backendmusica-production.up.railway.app"));
        
        // Permitimos todos los métodos HTTP (GET, POST, PUT, DELETE, etc)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitimos todas las cabeceras
        configuration.setAllowedHeaders(List.of("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder codificadorContraseña(){
        return new BCryptPasswordEncoder();
    }
}