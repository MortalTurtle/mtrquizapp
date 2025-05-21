package com.mt.quiz.service;

import androidx.annotation.Nullable;

import com.mt.quiz.models.Group;
import com.mt.quiz.models.User;
import com.mt.quiz.models.apimodels.UserRaw;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
        @GET("users")
        Call<User> getUserByIdOrUsername(@Query("id") @Nullable String userId,
                                         @Query("username") @Nullable String username);
        @POST("users")
        Call<String> postUser(@Body UserRaw user);
        @PATCH("users")
        Call<Void> editUser(@Header("X-User-Auth-Token") String token, @Body UserRaw userToEdit);

        @POST("users/login")
        Call<String> login(@Body UserRaw userToEdit);

        @POST("groups")
        Call<String> createGroup(@Header("X-User-Auth-Token") String token, @Body Group group);

        @GET("groups/{id}")
        Call<Group> getGroup(@Header("X-User-Auth-Token") String token, @Path("id") String id);

        @PATCH("groups/{id}")
        Call<Group> editGroup(@Header("X-User-Auth-Token") String token, @Path("id") String id, @Body Group group);

        @POST("groups/{id}/join")
        Call<Void> joinGroup(@Header("X-User-Auth-Token") String token, @Path("id") String id);

        @GET("groups/{id}/roles")
        Call<String> getUserRole(@Header("X-User-Auth-Token") String token, @Path("id") String id);
}
