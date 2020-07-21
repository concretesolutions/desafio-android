package com.jsouza.repocatalog.data.remote.requeststatus

class RequestStatus {
    object ApiError : Throwable("You have loaded too many repositories.")
    object LoadError : Throwable("Houston, we had a problem here. Try again later.")
    object NullKeysError : Throwable("Both Keys are null")
}
