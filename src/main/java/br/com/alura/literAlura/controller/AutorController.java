package br.com.alura.literAlura.controller;

import br.com.alura.literAlura.dto.AutorDTO;
import br.com.alura.literAlura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/todos")
    public ResponseEntity<List<AutorDTO>> listarAutoresDosLivrosRegistrados() {
        try {
            List<AutorDTO> autores = autorService.listarAutoresDosLivrosRegistrados();
            return ResponseEntity.ok(autores);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/buscar_autor")
    public ResponseEntity<List<AutorDTO>> listarAutoresPorAno(@RequestBody Map<String, String> dados) {
        try {
            int ano = Integer.parseInt(dados.get("ano"));
            List<AutorDTO> autores = autorService.listarAutoresPorAno(ano);
            return ResponseEntity.ok(autores);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
