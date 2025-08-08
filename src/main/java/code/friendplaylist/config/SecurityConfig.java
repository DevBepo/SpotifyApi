package code.friendplaylist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login/**", "/login.html", "/css/**", "/error", "/css/**", "/js/**").permitAll()
                .requestMatchers("/user/me", "/user/auth-status", "/user/search").authenticated()
                .requestMatchers("/home.html").authenticated()
                .requestMatchers("/playlists", "/playlists/**", "/users/*/playlists").authenticated()
                .requestMatchers("/api/playlists/{playlistId}/rate").authenticated()
                .requestMatchers("/api/playlists/{playlistId}/comments").authenticated() // Comentários precisam de autenticação
                .requestMatchers("/api/playlists/{playlistId}/average").permitAll() // Média pode ser pública
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login.html")
                .defaultSuccessUrl("/home.html", true)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**", "/api/**")
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                    .sameOrigin()
                )
            )
            .cors(withDefaults());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}