package br.com.alura.literAlura.dto;

import java.util.List;

public class LivroDTO {
    private String titulo;
    private Integer numeroDownloads;
    private List<String> idiomas;
    private List<String> autores;

    public LivroDTO(String titulo, Integer numeroDownloads, List<String> idiomas, List<String> autores) {
        this.titulo = titulo;
        this.numeroDownloads = numeroDownloads;
        this.idiomas = idiomas;
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public List<String> getAutores() {
        return autores;
    }
}
