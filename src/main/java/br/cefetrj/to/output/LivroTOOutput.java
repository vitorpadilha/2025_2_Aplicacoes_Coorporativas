package br.cefetrj.to.output;

import java.io.Serializable;

import br.cefetrj.model.Livro;

public class LivroTOOutput implements Serializable {

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
        if (livro.getEditora() != null) {
            this.editora = new EditoraTOOutput(livro.getEditora());
        }
    }

    public LivroTOOutput(Livro livro, boolean carregarEditora) {
        EditoraTOOutput editoraTO = null;
        if (carregarEditora && livro.getEditora() != null) {
            editoraTO = new EditoraTOOutput(livro.getEditora());
        }
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autores = livro.getAutores();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.editora = editoraTO;
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
