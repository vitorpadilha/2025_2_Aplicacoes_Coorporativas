package br.cefetrj.to.output;

import java.io.Serializable;

import br.cefetrj.model.Livro;
import br.cefetrj.model.Usuario;

public class UsuarioTOOutput implements Serializable {

    private String nome;
    private Integer id;
    private String email;
    private String papel;

    public UsuarioTOOutput(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.papel = usuario.getPapel();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

}
