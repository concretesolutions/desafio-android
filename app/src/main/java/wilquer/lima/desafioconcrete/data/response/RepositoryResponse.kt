package wilquer.lima.desafioconcrete.data.response

import wilquer.lima.desafioconcrete.data.model.Repository

data class RepositoryResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Repository>
)