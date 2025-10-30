package br.cefetrj.to.output;

import java.util.List;

import br.cefetrj.model.Livro;

public class EditoraTOOutput {
    private List<LivroTOOutput> livros;
    private String nome;
    private Integer id;

    public EditoraTOOutput(Integer id, String nome, List<Livro> livros) {
        this.id = id;
        this.nome = nome;
        this.livros = livros.stream().map(livro -> {
            LivroTOOutput livroTO = new LivroTOOutput(livro.getId(), livro.getTitulo(), livro.getAutores());
            return livroTO;
        }).toList();
    }

    public Integer getId() {
        return id;
    }

    public List<LivroTOOutput> getLivros() {
        return livros;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLivros(List<LivroTOOutput> livros) {
        this.livros = livros;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
