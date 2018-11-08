package com.example.shosho.elsheikh.api;

import com.example.shosho.elsheikh.model.BooksResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Service {
    // /api_elshekh/Allpeotry/?api_token=100&lang=ar&section=book
    @POST("Allpeotry/")
    Call<BooksResponse> getBooksData(@Body Map<String,String> map);

}
