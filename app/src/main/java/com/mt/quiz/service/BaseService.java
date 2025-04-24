package com.mt.quiz.service;

import java.util.HashMap;
import java.util.function.Function;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {
    private static final String BASE_URL = "http://127.0.0.1/v1/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService apiService = retrofit.create(ApiService.class);
    private final HashMap<Class<?>, Function<String, Object>> convertStringValueToSomeClass = new HashMap<>();

    public BaseService() {
        convertStringValueToSomeClass.put(String.class, (String str) -> str);
        convertStringValueToSomeClass.put(Integer.class, (String str) -> Integer.valueOf(str));
    }

    protected void setNewFieldValueFromString(Object obj, String fieldName, String value)
            throws NoSuchFieldException, NumberFormatException {
        var field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        var fieldType = field.getType();
        try {
            field.set(obj, convertStringValueToSomeClass.get(fieldType).apply(value));
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}