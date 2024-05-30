package br.com.alura.literAlura.controller;

import br.com.alura.literAlura.dto.LivroDTO;
import br.com.alura.literAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivrosController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/buscar_livro")
    public ResponseEntity<LivroDTO> buscarLivroPorTitulo(@RequestBody Map<String, String> request) {
        try {
            String titulo = request.get("titulo");
            LivroDTO livro = livroService.buscarLivroPorTitulo(titulo);
            return new ResponseEntity<>(livro, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<LivroDTO>> listarLivrosRegistrados() {
        List<LivroDTO> livros = livroService.listarLivrosRegistrados();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @PostMapping("/livros_por_idioma")
    public ResponseEntity<List<String>> buscarLivrosPorIdioma(@RequestBody Map<String, String> dados) {
        try {
            String idioma = dados.get("idioma");
            List<String> titulosLivros = livroService.buscarLivrosPorIdioma(idioma);
            return ResponseEntity.ok(titulosLivros);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
