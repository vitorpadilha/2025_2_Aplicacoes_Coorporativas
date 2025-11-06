package br.cefetrj.security.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class GoogleAuthController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    /**
     * Endpoint que recebe o código retornado pelo Google e troca por tokens
     */
    @PostMapping("/google")
    public ResponseEntity<?> exchangeCodeForToken(@RequestParam("code") String code) {
        String tokenEndpoint = "https://oauth2.googleapis.com/token";

        // Monta o corpo da requisição
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenEndpoint)
                .queryParam("code", code)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("grant_type", "authorization_code");

        RestTemplate restTemplate = new RestTemplate();

        // Faz a requisição POST para o Google
        ResponseEntity<Map> response = restTemplate.postForEntity(
                builder.toUriString(), null, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> body = response.getBody();

            /*
             * O retorno contém algo como:
             * {
             * "access_token": "...",
             * "expires_in": 3599,
             * "refresh_token": "...",
             * "scope": "openid email profile",
             * "token_type": "Bearer",
             * "id_token": "eyJhbGciOiJSUzI1NiIs..."
             * }
             */
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Erro ao obter token do Google");
        }
    }
}
