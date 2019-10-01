package contarini.com.desafio_tembici.data.models

import java.io.Serializable

data class BaseRepositoriesResponse(var total_count: Int,
                                    var items : ArrayList<RepositoriesResponse>) : Serializable