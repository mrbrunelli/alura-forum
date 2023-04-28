package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Answer
import br.com.alura.forum.service.TopicService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class NewAnswerFormMapper(
    private val userService: UserService,
    private val topicService: TopicService
) : Mapper<NewAnswerForm, Answer> {
    override fun map(t: NewAnswerForm): Answer {
        val user = userService.findById(t.authorId)
        val topic = topicService.findByIdModel(t.topicId)
        return Answer(
            message = t.message,
            author = user,
            topic = topic
        )
    }
}