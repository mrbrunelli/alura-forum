package br.com.alura.forum.service

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.mapper.AnswerViewMapper
import br.com.alura.forum.model.Answer
import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerViewMapper: AnswerViewMapper
) {
    private var answers: List<Answer> = listOf()

    init {
        val topic = Topic(
            id = 2,
            title = "Dúvida sobre data classe",
            message = "Como implementar interfaces?",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "programação",
            ),
            author = User(
                id = 1,
                name = "Brunelli",
                email = "brunelli@email.com.br",
            ),
        )
        answers = listOf(
            Answer(
                id = 1,
                message = "data class não utiliza interfaces",
                author = User(
                    id = 1,
                    name = "Espaço nave Jefferson",
                    email = "espaco_nave_jefferson@email.com.br"
                ),
                topic = topic,
                isSoluction = false
            ),
            Answer(
                id = 1,
                message = "data class MyClass() : MyInterface",
                author = User(
                    id = 2,
                    name = "Malucao",
                    email = "malucao@email.com.br"
                ),
                topic = topic,
                isSoluction = false
            )
        )
    }

    fun listByTopicId(id: Long): List<AnswerView> {
        return answers.filter { it.topic.id == id }.map { answerViewMapper.map(it) }
    }
}