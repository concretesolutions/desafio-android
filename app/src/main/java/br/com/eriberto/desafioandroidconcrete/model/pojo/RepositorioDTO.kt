package br.com.eriberto.desafioandroidconcrete.model.pojo

import java.io.Serializable

class RepositorioDTO (
    var total_count: Long,
    var incomplete_results: Boolean,
    var items: ArrayList<Repositorio>
):Serializable