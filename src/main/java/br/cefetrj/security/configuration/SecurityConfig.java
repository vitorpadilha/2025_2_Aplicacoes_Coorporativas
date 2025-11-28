package br.cefetrj.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import br.cefetrj.security.CustomAccessDeniedHandler;
import br.cefetrj.security.CustomAuthEntryPoint;
import br.cefetrj.security.CustomJwtAuthConverter;
import br.cefetrj.service.UsuarioService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
        @Autowired
        private UsuarioService usuarioService;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                        CorsConfigurationSource corsConfigurationSource)
                        throws Exception {
                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                                .csrf(csrf -> csrf.disable()) // IMPORTANTE: Desabilita CSRF para APIs
                                .authorizeHttpRequests(authz -> authz

                                                .requestMatchers("/auth/google/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                // .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                                // SE Não Fosse restful.oauth2Login(oauth -> oauth.loginPage("/login"))
                                .oauth2ResourceServer(oauth2 -> oauth2
                                                .authenticationEntryPoint(new CustomAuthEntryPoint())
                                                .accessDeniedHandler(new CustomAccessDeniedHandler())
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                                                new CustomJwtAuthConverter(usuarioService))));

                return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }

}