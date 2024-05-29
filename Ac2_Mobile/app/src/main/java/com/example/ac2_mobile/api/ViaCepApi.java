package com.example.ac2_mobile.api;

import com.example.ac2_mobile.models.EnderecoCep;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

    public interface ViaCepApi {
         @GET("ws/{cep}/json/")
        Call<EnderecoCep> getCepInfo(@Path("cep") String cep);
}