package com.mt.quiz.service;

import androidx.annotation.Nullable;

import com.mt.quiz.models.User;
import com.mt.quiz.models.apimodels.UserRaw;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
        @GET("users")
        Call<User> getUserByIdOrUsername(@Query("id") @Nullable String userId,
                                         @Query("username") @Nullable String username);
        @POST("users")
        Call<Void> postUser(@Body UserRaw user);
        @PATCH("users")
        Call<Void> editUser(@Header("X-User-Auth-Token") String token, @Body UserRaw userToEdit);

        @POST("users/login")
        Call<String> login(@Body UserRaw userToEdit);
}
