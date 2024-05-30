package br.com.alura.literAlura.repository;

import br.com.alura.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAll();

    @Query("SELECT autor FROM Autor autor WHERE autor.anoNascimento < :ano AND (autor.anoMorte IS NULL OR autor.anoMorte > :ano)")
    List<Autor> findAutoresPorAno(int ano);
}
