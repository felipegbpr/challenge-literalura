package com.alura.literalura.repository;


import com.alura.literalura.model.entity.Idioma;
import com.alura.literalura.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
   @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
   List<Livro> obterLivrosPorIdioma(Idioma idioma);

   Optional<Livro> findByTitulo(String titulo);
}
