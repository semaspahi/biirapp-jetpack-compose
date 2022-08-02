package com.sema.base.common


data class Resource<out T>(val status: Status, val data: T?, val error: Error? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable? = null): Resource<T> {
            return Resource(Status.ERROR, null, HttpErrorParser.parseError(throwable))
        }

        fun <T> error(error: Error? = null): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

    }

}