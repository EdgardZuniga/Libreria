package com.AluraChallenge.Library.Options;

import com.AluraChallenge.Library.Books.IData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertResponse implements IData {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T DataConverter(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
