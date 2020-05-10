package com.hotmail.fignunes.desafioconcretesolution.common.extensions

fun String.reverseDateToBar(): String {
    return this.substring(8,10) + "/" + this.substring(5,7) + "/" + this.substring(0,4)
}
