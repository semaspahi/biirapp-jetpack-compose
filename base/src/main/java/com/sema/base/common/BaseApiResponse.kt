package com.sema.base.common

import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { return Resource.success(it) }
            } else {
                val errorBody: HttpErrorData = Gson().fromJson(response.errorBody()!!.charStream(), HttpErrorData::class.java)
                return Resource.error(Error(ErrorType.Http, HttpErrorData(response.code(), errorBody.message, errorBody.errorKey), response.code()))
            }
            return Resource.error(Error(ErrorType.Unknown))
        } catch (e: Exception) {
            return Resource.error(e)
        }
    }
}