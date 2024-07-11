package com.AluraChallenge.Library.Books;

public interface IData {
    public <T> T DataConverter(String json, Class<T> classType);
}
