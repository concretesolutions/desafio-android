package br.com.henriqueoliveira.desafioandroidconcrete.service.repository

class Resource<T> constructor(val status: RequestStatus,
                              val data: T? = null,
                              val message: String? = null) {

    companion object {
        fun <T> loading(data: T? = null): Resource<T> = Resource(RequestStatus.LOADING, data)
        fun <T> success(data: T?): Resource<T> = Resource(RequestStatus.SUCCESS, data)
        fun <T> error(msg: String?, data: T? = null): Resource<T> = Resource(RequestStatus.ERROR, data, msg)
    }


    enum class RequestStatus {
        LOADING,
        SUCCESS,
        ERROR
    }
}