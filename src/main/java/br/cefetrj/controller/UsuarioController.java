package br.cefetrj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.UsuarioService;
import br.cefetrj.to.input.UsuarioTOInput;

import br.cefetrj.to.output.UsuarioTOOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/usuarios", tags = { "Usuarios - UsuarioController" })
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<UsuarioTOOutput> save(@AuthenticationPrincipal OAuth2User principal,
            @RequestBody UsuarioTOInput input) {
        final var usuario = input.build();
        final Usuario created = usuarioService.save(usuario);

        return new ResponseEntity<>(new UsuarioTOOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<UsuarioTOOutput> edit(@AuthenticationPrincipal OAuth2User principal,
            @RequestBody UsuarioTOInput input) {
        final Usuario usuario = input.build();
        Usuario usuarioLogado = usuarioService.findByEmail(principal.getAttribute("email")).orElse(null);
        usuario.setAlteradoPor(usuarioLogado);
        final Usuario updated = usuarioService.update(usuario);

        return new ResponseEntity<>(new UsuarioTOOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<UsuarioTOOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(usuarioService.findById(id).map(UsuarioTOOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<UsuarioTOOutput>> findAll() {

        return ResponseEntity.ok(usuarioService.findAll().stream().map(UsuarioTOOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        usuarioService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/pegaPorEmail")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    // localhost:8080/AppCorporativaMavenWeb/usuarios/pegaPorEmail
    public ResponseEntity<UsuarioTOOutput> findByEmail(
            @RequestParam(required = false) String email

    ) {

        return usuarioService.findByEmail(email)
                .map(u -> ResponseEntity.ok(new UsuarioTOOutput(u)))
                .orElse(ResponseEntity.notFound().build());
    }
}
