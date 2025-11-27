package br.cefetrj.security.configuration;

import java.util.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.cefetrj.service.UsuarioService;

public class CustomJwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final UsuarioService usuarioService;

    public CustomJwtAuthConverter(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        String email = jwt.getClaimAsString("email");

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // papel default

        var usuario = usuarioService.findByEmail(email).orElse(null);
        if (usuario != null) {
            // adiciona papel do banco
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getPapel()));
        }

        return new JwtAuthenticationToken(jwt, authorities);
    }
}