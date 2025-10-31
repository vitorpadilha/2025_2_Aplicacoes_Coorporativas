package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Livro;
import jakarta.persistence.criteria.CriteriaBuilder.In;

public class LivroTOInput implements Serializable {
    private String titulo;
    private Integer id;
    private String autores;
    private EditoraTOInput editora;
    private Integer anoPublicacao;

    public Integer getId() {
        return id;
    }

    public String getAutores() {
        return autores;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setEditora(EditoraTOInput editora) {
        this.editora = editora;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public EditoraTOInput getEditora() {
        return editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public Livro build() {
        var livro = new Livro();
        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setAutores(autores);
        livro.setEditora(editora.build());
        livro.setAnoPublicacao(anoPublicacao);
        return livro;
    }
}
