package com.hako.githubapi.features.pullRequest.exceptions

class ArgumentMissingException(private val argument: String) : Exception() {
    override val message: String?
        get() = "The argument [$argument] are required for this feature to work."
}
