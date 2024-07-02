package com.alura.literalura.service.provider;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

}
