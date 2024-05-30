package br.com.alura.literAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer numeroDownloads;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idiomas",
            joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma")
    private List<String> idiomas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "livros_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Long getId() {
        return id;
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

    public List<Autor> getAutores() {
        return autores;
    }
}



