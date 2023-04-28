package br.com.alura.forum.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class NewAnswerForm(
    @field:NotEmpty
    val message: String,
    @field:Min(1)
    val authorId: Long,
    @field:Min(1)
    val topicId: Long
)
