package com.mt.quiz.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mt.quiz.models.apimodels.BadRequestError;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {
    private static class MixedConverter extends Converter.Factory {
        private final Gson gson = new Gson();
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(
                Type type,
                Annotation[] annotations,
                Retrofit retrofit
        ) {
            if (type == String.class)
                return (Converter<ResponseBody, String>) ResponseBody::string;
            return (Converter<ResponseBody, Object>) value -> {
                try {
                    return gson.fromJson(value.charStream(), type);
                } catch (JsonSyntaxException e) {
                    return value.string();
                }
            };
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(
                Type type,
                Annotation[] parameterAnnotations,
                Annotation[] methodAnnotations,
                Retrofit retrofit
        ) {
            return GsonConverterFactory.create(gson)
                    .requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
    }
    private static final String BASE_URL = "http://192.168.1.193:8080/v1/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(new MixedConverter())
            .build();

    public static ApiService apiService = retrofit.create(ApiService.class);

    public static BadRequestError parseError(Response<?> response) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(response.errorBody().charStream(), BadRequestError.class);
        } catch (Exception e) {
            return null;
        }
    }

    protected static<T> Response<T> wrapHttpRequest(Call<T> call) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Response<T>> f = executor.submit(() -> {
            try {
                return call.execute();
            } catch (IOException e) {
                return null;
            }
        });
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        } finally {
            executor.shutdown();
        }
    }
}