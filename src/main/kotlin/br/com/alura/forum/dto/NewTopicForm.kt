package br.com.alura.forum.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(
    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val title: String,
    @field:NotEmpty
    @field:Size(min = 10, max = 1000)
    val message: String,
    @field:NotNull
    @field:Min(1)
    val courseId: Long,
    @field:NotNull
    @field:Min(1)
    val authorId: Long,
)
