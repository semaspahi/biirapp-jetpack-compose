package com.sema.base.data.api

import com.sema.base.data.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/beers")
    suspend fun getBeers(@Query("page") page: Int? = null): Response<List<Beer>>

    @GET("/v2/beers/{id}")
    suspend fun getBeer(@Path("id") page: Int): Response<List<Beer>>
}
