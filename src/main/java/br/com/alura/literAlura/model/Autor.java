package br.com.alura.literAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoMorte;

    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    public void setLivros(List<Livro> livros){
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
