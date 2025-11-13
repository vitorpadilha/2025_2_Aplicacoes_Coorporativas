package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Usuario;

public class UsuarioTOInput implements Serializable {
    private String nome;
    private Integer id;
    private String email;
    private String papel;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPapel() {
        return papel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public Usuario build() {
        var usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setPapel(papel);
        return usuario;
    }
}
