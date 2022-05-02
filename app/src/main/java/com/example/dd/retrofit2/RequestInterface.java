package com.example.dd.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;

// Interface for requesting the JSON from the API
public interface RequestInterface {
    @GET("Jarvi")
    Call<JSONResponse> getJSON();
}
