package br.com.alura.literAlura.service;

import br.com.alura.literAlura.dto.AutorDTO;
import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repositorioAutor;

    public List<AutorDTO> listarAutoresDosLivrosRegistrados() {
        List<Autor> autores = repositorioAutor.findAll();
        return autores.stream()
            .map(a -> new AutorDTO(
                a.getNome(),
                a.getAnoNascimento(),
                a.getAnoMorte()
            )).collect(Collectors.toList());
    }


    public List<AutorDTO> listarAutoresPorAno(int ano) {
        List<Autor> autores = repositorioAutor.findAutoresPorAno(ano);
        return autores.stream()
            .map(a -> new AutorDTO(a.getNome(), a.getAnoNascimento(), a.getAnoMorte()))
            .collect(Collectors.toList());
    }
}
