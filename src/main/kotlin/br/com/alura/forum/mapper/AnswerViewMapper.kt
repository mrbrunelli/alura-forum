package br.com.alura.forum.mapper

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.model.Answer
import org.springframework.stereotype.Component

@Component
class AnswerViewMapper(
    private val authorViewMapper: AuthorViewMapper
) : Mapper<Answer, AnswerView> {
    override fun map(t: Answer): AnswerView {
        return AnswerView(
            id = t.id,
            message = t.message,
            isSolution = t.isSolution,
            createdAt = t.createdAt,
            author = authorViewMapper.map(t.author)
        )
    }
}
