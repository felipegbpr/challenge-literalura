package com.alura.literalura.domain;

import com.alura.literalura.model.entity.Autor;
import com.alura.literalura.model.entity.Livro;

import java.util.stream.Collectors;

public record AutorDto(
        String nome,
        Integer anoNascimento,
        Integer anoFalecimento,
        String livrosEscritos
) {
    public AutorDto(Autor autor) {
        this(
                autor.getNome(),
                autor.getAnoNascimento(),
                autor.getAnoFalecimento(),
                autor.getLivros()
                        .stream()
                        .map(Livro::getTitulo)
                        .collect(Collectors.joining(", "))
        );
    }

    @Override
    public String toString() {
        return """
                Autor: %s
                Data de nascimento: %d
                Data de falecimento: %d
                Livros: [%s] 
                """.formatted(nome, anoNascimento, anoFalecimento, livrosEscritos);
    }
}
