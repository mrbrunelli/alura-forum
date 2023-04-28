package br.com.alura.forum.service

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.mapper.AnswerViewMapper
import br.com.alura.forum.mapper.NewAnswerFormMapper
import br.com.alura.forum.model.Answer
import br.com.alura.forum.repository.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val repository: AnswerRepository,
    private val newAnswerFormMapper: NewAnswerFormMapper,
) {
    fun create(dto: NewAnswerForm) {
        val answer = newAnswerFormMapper.map(dto)
        repository.save(answer)
    }
}