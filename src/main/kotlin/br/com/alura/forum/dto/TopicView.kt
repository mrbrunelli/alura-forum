package br.com.alura.forum.dto

import br.com.alura.forum.model.TopicStatus
import java.io.Serializable
import java.time.LocalDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
): Serializable
