package br.cefetrj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import br.cefetrj.model.Editora;
import br.cefetrj.model.Livro;
import br.cefetrj.service.LivroService;
import br.cefetrj.to.input.EditoraTOInput;
import br.cefetrj.to.input.LivroTOInput;
import br.cefetrj.to.output.EditoraTOOutput;
import br.cefetrj.to.output.LivroTOOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/livros", tags = { "Livros - LivroController" })
public class LivroController {
    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<LivroTOOutput> save(@RequestBody LivroTOInput input) {
        final var livro = input.build();

        final Livro created = livroService.save(livro);

        return new ResponseEntity<>(new LivroTOOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<LivroTOOutput> edit(@RequestBody LivroTOInput input) {

        final Livro updated = livroService.update(input.build());

        return new ResponseEntity<>(new LivroTOOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<LivroTOOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(livroService.findById(id).map(LivroTOOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<LivroTOOutput>> findAll() {

        return ResponseEntity.ok(livroService.findAll().stream().map(LivroTOOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        livroService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
