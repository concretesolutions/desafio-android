package br.com.eriberto.desafioandroidconcrete.model.interfaces

import br.com.eriberto.desafioandroidconcrete.model.pojo.ForkRepositorio
import br.com.eriberto.desafioandroidconcrete.model.pojo.Repositorio

interface InteracaoComListaFork {

    fun buscarMais(numeroDaPagina: Int)
    fun selecionou(fork: ForkRepositorio)
}