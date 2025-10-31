package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Editora;

public class EditoraTOInput implements Serializable {
    private String nome;
    private Integer id;

    public EditoraTOInput() {
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Editora build() {
        var editora = new Editora();
        editora.setId(id);
        editora.setNome(nome);
        return editora;
    }
}
