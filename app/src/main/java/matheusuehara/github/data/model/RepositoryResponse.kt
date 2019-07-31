package matheusuehara.github.data.model

import java.util.*

data class RepositoryResponse(var total_count: Int,
                              var isIncomplete_results: Boolean,
                              var items: ArrayList<Repository>?)
