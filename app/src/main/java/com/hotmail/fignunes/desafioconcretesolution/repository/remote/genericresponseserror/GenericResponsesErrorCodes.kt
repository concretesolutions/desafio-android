package com.hotmail.fignunes.desafioconcretesolution.repository.remote.genericresponseserror

enum class GenericResponsesErrorCodes(val code: Int) {
    LIMIT_EXCEEDED(403),
    LIMIT_SEARCH_EXCEEDED(422),
    UNAVAILABLE_SERVICE(404),
    ERROR_UNEXPECTED(500)
}