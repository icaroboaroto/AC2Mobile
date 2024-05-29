package com.example.ac2_mobile.api;


import com.example.ac2_mobile.models.Alunos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

    public interface ApiService {
        @GET("Alunos")
        Call<List<Alunos>> getAlunos();

        @POST("Aluno")
        Call<Alunos> createAluno(@Body Alunos aluno);
    }
}
