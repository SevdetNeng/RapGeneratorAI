package com.sevdetneng.rapgeneratorai.domain.model.local

sealed class NetworkResponse<out T>(val data : T?,val message : String?,val status : ResponseStatus?){
    data class Success<out T>(val _data : T) : NetworkResponse<T>(
        data = _data,
        message = null,
        status = ResponseStatus.SUCCESS
    )
    data class Error(val _message : String) : NetworkResponse<Nothing>(
        message =  _message,
        data = null,
        status = ResponseStatus.ERROR
    )
    data object Loading : NetworkResponse<Nothing>(
        message = null,
        data = null,
        status = ResponseStatus.LOADING
    )
}
enum class ResponseStatus{
    LOADING,
    SUCCESS,
    ERROR
}