package com.alura.literalura.service;

import com.alura.literalura.domain.AutorDto;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public List<AutorDto> obterAutores() {
        return repository.findAll().stream()
                .map(autor -> new AutorDto(autor))
                .toList();
    }

    public List<AutorDto> obterAutoresVivosPorAno(int ano) {
        return repository.obterAutoresVivosPorAno(ano).stream()
                .map(autor -> new AutorDto(autor))
                .toList();
    }


}
