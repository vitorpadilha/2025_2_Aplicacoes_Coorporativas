package br.cefetrj.to.output;

public class LivroTOOutput {

    private Integer id;
    private String titulo;
    private String autores;

    public LivroTOOutput(Integer id, String titulo, String autores) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
