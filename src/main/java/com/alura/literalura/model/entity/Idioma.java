package com.alura.literalura.model.entity;

public enum Idioma {

    INGLES("en", "Inglês"),
    ESPANHOL("es", "Espanhol"),
    FRANCES("fr", "Francês"),
    PORTUGUES("pt", "Português");

    private String abreviatura;
    private String nome;

    Idioma(String abreviatura, String nome) {
        this.abreviatura = abreviatura;
        this.nome = nome;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.abreviatura.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado: " + text);
    }

    public String getNome() {
        return nome;
    }

    public String apresentar() {
        return abreviatura + " - " + nome;
    }

    @Override
    public String toString() {
        return abreviatura;
    }
}
