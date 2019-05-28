package cl.jesualex.desafio_android.repo.data.domain.entity

/**
 * Created by jesualex on 2019-05-28.
 */

data class Links (
    val self : Link,
    val html : Link,
    val issue : Link,
    val comments : Link,
    val review_comments : Link,
    val review_comment : Link,
    val commits : Link,
    val statuses : Link
)