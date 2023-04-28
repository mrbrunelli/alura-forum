package br.com.alura.forum.dto

import br.com.alura.forum.model.Author
import java.time.LocalDateTime

data class AnswerView(
    val id: Long?,
    val message: String,
    val author: AuthorView,
    val isSolution: Boolean,
    val createdAt: LocalDateTime
)
