package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.model.Answer
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class NewAnswerFormMapper(
    private val userService: UserService,
) : Mapper<NewAnswerForm, Answer> {
    override fun map(t: NewAnswerForm): Answer {
        val user = userService.findById(t.authorId) ?: throw Error("Author not found")
        return Answer(
            message = t.message,
            author = user
        )
    }
}