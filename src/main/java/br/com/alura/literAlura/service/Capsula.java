package br.com.alura.literAlura.service;

import br.com.alura.literAlura.model.DadosLivro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Capsula {

    private List<DadosLivro> results;

    public List<DadosLivro> getResults(){
        return results;
    }

    public void setResults(List<DadosLivro> results){
        this.results = results;
    }
}