package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicView
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = TopicView(
        id = 1,
        title = "Kotlin básico",
        message = "Aprendendo kotlin basico",
        status = TopicStatus.NOT_ANSWERED,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}