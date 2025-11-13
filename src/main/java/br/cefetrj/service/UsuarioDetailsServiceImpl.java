package br.cefetrj.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.cefetrj.model.Usuario;
import br.cefetrj.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        var authorities2 = new ArrayList<SimpleGrantedAuthority>();
        authorities2.add(new SimpleGrantedAuthority(usuario.getPapel()));

        return new org.springframework.security.core.userdetails.User(
                usuario.getNome(),
                "",
                usuario.isAtivo(),
                true,
                true,
                true,
                authorities2);
    }

}
