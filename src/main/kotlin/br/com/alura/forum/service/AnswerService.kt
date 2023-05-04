package br.com.alura.forum.service

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.mapper.AnswerViewMapper
import br.com.alura.forum.mapper.NewAnswerFormMapper
import br.com.alura.forum.repository.AnswerRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val repository: AnswerRepository,
    private val newAnswerFormMapper: NewAnswerFormMapper,
    private val answerViewMapper: AnswerViewMapper,
    private val emailService: EmailService
) {
    @CacheEvict(value = ["answers"], allEntries = true)
    fun create(dto: NewAnswerForm) {
        val answer = newAnswerFormMapper.map(dto)
        val authorEmail = answer.topic?.author?.email ?: throw RuntimeException("author's email is required")
        repository.save(answer)
        emailService.notify(authorEmail)
    }

    @Cacheable(cacheNames = ["answers"], key = "#root.method.name")
    fun listByTopicId(topicId: Long, pagination: Pageable): Page<AnswerView> {
        val answers = repository.findByTopicId(topicId, pagination)
        return answers.map { answerViewMapper.map(it) }
    }
}