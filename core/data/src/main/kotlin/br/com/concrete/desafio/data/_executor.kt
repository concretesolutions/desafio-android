package br.com.concrete.desafio.data

internal fun async(block: () -> Unit) = Thread(block).apply { start() }