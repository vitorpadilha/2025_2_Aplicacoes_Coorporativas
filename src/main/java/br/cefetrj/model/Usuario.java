package br.cefetrj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario extends Pessoa {

    private String email;
    private boolean ativo;
    private String papel; // e.g., "ADMIN", "USER"

    // Getters e Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
