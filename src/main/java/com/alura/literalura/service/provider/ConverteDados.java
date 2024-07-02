package com.alura.literalura.service.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    private static ConverteDados instance;
    private ObjectMapper objectMapper = new ObjectMapper();

    private ConverteDados() {}

    public static synchronized ConverteDados getInstance() {
        if (instance == null) {
            instance = new ConverteDados();
        }
        return instance;
    }

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
