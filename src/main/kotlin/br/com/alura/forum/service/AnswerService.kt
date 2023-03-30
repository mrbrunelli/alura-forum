package br.com.alura.forum.service

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.mapper.AnswerViewMapper
import br.com.alura.forum.mapper.NewAnswerFormMapper
import br.com.alura.forum.model.Answer
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerViewMapper: AnswerViewMapper,
    private val newAnswerFormMapper: NewAnswerFormMapper,
    private val topicService: TopicService
) {
    private var answers: MutableList<Answer> = mutableListOf()

    fun listByTopicId(id: Long): List<AnswerView> {
        return answers.filter { it.topic?.id == id }.map { answerViewMapper.map(it) }
    }

    fun create(dto: NewAnswerForm, topicId: Long) {
        val answer = newAnswerFormMapper.map(dto)
        answer.id = answers.size.toLong() + 1
        answer.topic = topicService.findByIdModel(topicId)
        answers.add(answer)
    }
}