package br.cefetrj;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class BibliotecaApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BibliotecaApplication.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterInit() throws IOException, URISyntaxException, ClassNotFoundException {
        System.out.println("Aplicação iniciada com sucesso!");
    }

    // SOLUÇÃO DE EMERGÊNCIA - Filtro CORS global
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(false);
        config.addAllowedOrigin("http://localhost:81");
        config.addAllowedOrigin("http://127.0.0.1:81");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*"); // Permite todos os métodos

        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }
}