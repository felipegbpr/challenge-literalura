package com.alura.literalura.ui;

import com.alura.literalura.domain.AutorDto;
import com.alura.literalura.domain.LivroDto;
import com.alura.literalura.model.Dados;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.entity.Idioma;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.LivroService;
import com.alura.literalura.service.provider.ConsumoAPI;
import com.alura.literalura.service.provider.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class AppConsole {

    private static final String URL_BASE = "https://gutendex.com/books/";

    @Autowired
    private LivroService livroService;
    @Autowired
    private AutorService autorService;

    private Scanner scan = new Scanner(System.in);
    private ConsumoAPI consumoApi = ConsumoAPI.getInstance();
    private ConverteDados conversor = ConverteDados.getInstance();

    public void executarOperacoes() {
        int operacao = -1;
        while (operacao != 0) {
           apresentarMenu();
           try {
            operacao = scan.nextInt();
           } catch (InputMismatchException e) {
               System.out.println("Opções válidas: 0, 1, 2, 3, 4, 5");
               continue;
           } finally {
               scan.nextLine();
           }
           switch (operacao) {
               case 1:
                   buscarLivrosPorTitulo();
                   break;
               case 2:
                   listarLivrosRegistrados();
                   break;
               case 3:
                   listarAutoresRegistrados();
                   break;
               case 4:
                   listarAutoresVivosPorAno();
                   break;
               case 5:
                   apresentarIdiomas();
                   listarLivrosPorIdioma();
                   break;
           }
        }
    }

    public void apresentarMenu() {
        System.out.println("""
                *************************************************
                                 Menu de opções
                *************************************************
                1 - Buscar livro por titulo
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros por idioma
                0 - Sair
                -------------------------------------------------
                Escolha a opção através do seu número: """);
    }

    public void apresentarIdiomas() {
        for (Idioma idioma : Idioma.values()) {
            System.out.println(idioma.apresentar());
        }
    }

    private void buscarLivrosPorTitulo() {
        System.out.println("Digite o nome do livro que deseja buscar");
        String titulo = scan.nextLine();

        String json = consumoApi.obterDados(URL_BASE + "?search=" + titulo.replace(" ", "+"));

        DadosLivro livroBuscado = conversor.obterDados(json, Dados.class).livros().get(0);
        LivroDto livroNovo = livroService.salvarLivro(livroBuscado);
        System.out.println(livroNovo);
    }

    private void listarLivrosRegistrados() {
        List<LivroDto> livroRegistrados = livroService.obterLivros();
        livroRegistrados.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<AutorDto> autoresRegitrados = autorService.obterAutores();
        autoresRegitrados.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Insira o ano do(s) autor(es) vivo(s) que deseja pesquisar: ");
        int ano = 0;
        try {
            ano = scan.nextInt();
            List<AutorDto> autoresVivos = autorService.obterAutoresVivosPorAno(ano);
            autoresVivos.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Insira um ano inválido. Não foi possível realizar a pesquisa.");
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma desejado: ");
        String abreviatura = scan.nextLine();
        Idioma idioma = Idioma.fromString(abreviatura);

        List<LivroDto> livrosPorIdioma = livroService.obterLivrosPorIdioma(idioma);
        livrosPorIdioma.forEach(System.out::println);
    }

}
