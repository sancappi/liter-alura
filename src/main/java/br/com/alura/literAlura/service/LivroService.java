package br.com.alura.literAlura.service;

import br.com.alura.literAlura.dto.LivroDTO;
import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.model.DadosLivro;
import br.com.alura.literAlura.model.Livro;
import br.com.alura.literAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private ConsumoDadosDaAPI consumoAPI;

    @Autowired
    private LivroRepository repositorioLivro;


    private final String URL_DA_API = "https://gutendex.com/books/?search=";

    public LivroDTO buscarLivroPorTitulo(String tituloLivro) {
        ConverteDados conversor = new ConverteDados();
        var jsonRecebido = consumoAPI.obterDadosDaAPI(URL_DA_API + tituloLivro.replace(" ", "+"));
        Capsula capsula = conversor.obterDados(jsonRecebido, Capsula.class);
        List<DadosLivro> dadosLivro = capsula.getResults();
        DadosLivro livroEncontrado = null;

        for (DadosLivro livro : dadosLivro) {
            if (livro.titulo().equalsIgnoreCase(tituloLivro)) {
                livroEncontrado = livro;
                break;
            }
        }

        if (livroEncontrado != null) {
            Livro livro = new Livro();
            livro.setTitulo(livroEncontrado.titulo());
            livro.setNumeroDownloads(livroEncontrado.numeroDownloads());
            livro.setIdiomas(livroEncontrado.idiomas());

            List<Autor> autores = new ArrayList<>();
            for (DadosLivro.Autor autorDados : livroEncontrado.autores()) {
                Autor autor = new Autor();
                autor.setNome(autorDados.nome());
                autor.setAnoNascimento(autorDados.anoNascimento());
                autor.setAnoMorte(autorDados.anoMorte());
                autores.add(autor);
            }
            livro.setAutores(autores);
            Livro livroSalvo = repositorioLivro.save(livro);

            List<String> nomesAutores = livroSalvo.getAutores().stream()
                    .map(Autor::getNome)
                    .collect(Collectors.toList());

            return new LivroDTO(livroSalvo.getTitulo(), livroSalvo.getNumeroDownloads(), livroSalvo.getIdiomas(), nomesAutores);
        } else {
            throw new RuntimeException("Nenhum livro encontrado para o título fornecido.");
        }
    }

    public List<LivroDTO> listarLivrosRegistrados() {
        List<Livro> livros = repositorioLivro.findAll();
        return livros.stream().map(l -> {
            List<String> autoresNomes = l.getAutores().stream().map(Autor::getNome).collect(Collectors.toList());
            return new LivroDTO(l.getTitulo(), l.getNumeroDownloads(), l.getIdiomas(), autoresNomes);
        }).collect(Collectors.toList());
    }

    public List<String> buscarLivrosPorIdioma(String idioma) {
        List<Livro> livrosPorIdioma = repositorioLivro.findByIdiomasContaining(idioma);

        if (livrosPorIdioma.isEmpty()) {
            return Collections.emptyList();
        } else {
            return livrosPorIdioma.stream().map(Livro::getTitulo).collect(Collectors.toList());
        }
    }
}
