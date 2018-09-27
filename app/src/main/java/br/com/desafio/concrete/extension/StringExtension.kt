package br.com.desafio.concrete.extension

/**
 * Created by Malkes on 26/09/2018.
 */

fun String.toHtmlColored(hexColor: String): String{

    return "<font color=$hexColor>$this</font>"

}