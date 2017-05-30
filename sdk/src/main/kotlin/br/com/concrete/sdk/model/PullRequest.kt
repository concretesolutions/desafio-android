package br.com.concrete.sdk.model

import br.com.concrete.sdk.model.type.State
import com.google.gson.annotations.Expose

data class PullRequest(
        @Expose val id: Long,
        @Expose @State val state: String,
        @Expose val title: String,
        @Expose val user: User,
        @Expose val body: String
)