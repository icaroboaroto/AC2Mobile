package com.example.ac2_mobile.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MockApi {

    public static ApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://665667249f970b3b36c5452e.mockapi.io/aluno")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
