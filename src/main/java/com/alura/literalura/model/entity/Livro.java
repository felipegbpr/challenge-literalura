package com.alura.literalura.model.entity;

import com.alura.literalura.model.DadosLivro;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String temas;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer contadorDownloads;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "livros_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;

    public Livro() {}

    public Livro(DadosLivro livro) {
        this.titulo = livro.titulo();
        this.temas = livro.temas().stream()
                .collect(Collectors.joining(";"));
        this.idioma = Idioma.fromString(livro.idiomas().get(0));
        this.contadorDownloads = livro.contadorDownloads();
        this.autores = livro.autore().stream()
                .map(dta -> new Autor(dta))
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getContadorDownloads() {
        return contadorDownloads;
    }

    public void setContadorDownloads(Integer contadorDownloads) {
        this.contadorDownloads = contadorDownloads;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        String listaAutores = autores.stream()
                .map(Autor::getNome)
                .collect(Collectors.joining(", "));

        return """
                ----------- Livro -----------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Número de downloads: %d
                -----------------------------
                """.formatted(
                        titulo, listaAutores, idioma, contadorDownloads);
    }
}
