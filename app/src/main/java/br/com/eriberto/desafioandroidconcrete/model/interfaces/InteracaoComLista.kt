package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio

interface InteracaoComLista {

    fun buscarMais(numeroDaPagina: Int)
    fun selecionou(repositorio: Repositorio)
}