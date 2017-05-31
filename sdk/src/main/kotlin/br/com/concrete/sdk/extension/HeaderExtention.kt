package br.com.concrete.sdk.extension

internal fun String?.extractPage(rev: String): Int? {
    val link = this?.split(',')?.filter { it.contains(rev) }?.firstOrNull()
    return link?.substring(link.indexOf("page=") + 5)?.split("&")?.firstOrNull()?.toInt()
}