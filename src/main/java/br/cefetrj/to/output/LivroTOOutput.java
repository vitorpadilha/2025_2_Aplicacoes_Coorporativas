package br.cefetrj.to.output;

import br.cefetrj.model.Livro;

public class LivroTOOutput {

    private Integer id;
    private String titulo;
    private String autores;
    private Integer anoPublicacao;
    private EditoraTOOutput editora;

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public LivroTOOutput(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autores = livro.getAutores();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.editora = new EditoraTOOutput(livro.getEditora());
    }

    public EditoraTOOutput getEditora() {
        return editora;
    }

    public void setEditora(EditoraTOOutput editora) {
        this.editora = editora;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

}
