package br.com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro (
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<Autor> autores,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Integer numeroDownloads
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static record Autor (
            @JsonAlias("name") String nome,
            @JsonAlias("birth_year") Integer anoNascimento,
            @JsonAlias("death_year") Integer anoMorte
    ){}
}
