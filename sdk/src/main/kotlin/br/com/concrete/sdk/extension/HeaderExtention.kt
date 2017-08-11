@file:JvmName("HeaderUtils")

package br.com.concrete.sdk.extension

import retrofit2.Response

internal fun String?.extractPage(rev: String): Int? {
    val link = this?.split(',')?.filter { it.contains(rev) }?.firstOrNull()
    return link?.substring(link.indexOf("page=") + 5)?.split("&")?.firstOrNull()?.toInt()
}

internal fun <T> Response<T>.nextPage() = headers().get("Link").extractPage("next")