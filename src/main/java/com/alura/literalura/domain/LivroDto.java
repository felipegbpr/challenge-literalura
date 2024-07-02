package com.alura.literalura.domain;

import com.alura.literalura.model.entity.Autor;
import com.alura.literalura.model.entity.Livro;

import java.util.stream.Collectors;

public record LivroDto(
        String titulo,
        String tema,
        String idioma,
        Integer downloads,
        String autores
) {
    public LivroDto(Livro livro) {
        this(
                livro.getTitulo(),
                livro.getTemas(),
                livro.getIdioma().getNome(),
                livro.getContadorDownloads(),
                livro.getAutores()
                        .stream()
                        .map(Autor::getNome)
                        .collect(Collectors.joining(", "))
        );
    }

    @Override
    public String toString() {
        return """
                ----------- Livro -----------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de downloads: %d
                -----------------------------
                """.formatted(titulo, autores, idioma, downloads);
    }
}
