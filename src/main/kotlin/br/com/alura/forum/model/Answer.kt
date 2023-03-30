package br.com.alura.forum.model

import java.time.LocalDateTime

data class Answer(
    var id: Long? = null,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val author: User,
    var topic: Topic? = null,
    val isSoluction: Boolean = false
)
