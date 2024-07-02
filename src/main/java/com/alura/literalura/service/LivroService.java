package com.alura.literalura.service;

import com.alura.literalura.domain.LivroDto;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.entity.Idioma;
import com.alura.literalura.model.entity.Livro;
import com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    public LivroDto salvarLivro(DadosLivro dados) {
        Optional<Livro> novoLivro = repository.findByTitulo(dados.titulo());

        if (!novoLivro.isPresent()) {
            Livro livro = repository.save(new Livro(dados));
            return new LivroDto(livro);
        } else {
            System.out.println("Livro já existente na base de dados");
        }

        return new LivroDto(novoLivro.get());
    }

    public LivroDto obterLivroPorId(Long id) {
        return repository.findById(id).map(LivroDto::new).orElse(null);
    }

    public List<LivroDto> obterLivros() {
        return repository.findAll().stream()
                .map(LivroDto::new)
                .toList();
    }

    public List<LivroDto> obterLivrosPorIdioma(Idioma idioma) {
        return repository.obterLivrosPorIdioma(idioma).stream()
                .map(LivroDto::new)
                .toList();
    }

}
