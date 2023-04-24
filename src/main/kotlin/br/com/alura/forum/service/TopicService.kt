package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.NewTopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val newTopicFormMapper: NewTopicFormMapper
) {
    private val notFoundMessage = "topic not found"

    fun list(name: String?, pagination: Pageable): Page<TopicView> {
        val topics = when (name) {
            null -> repository.findAll(pagination)
            else -> repository.findByCourseName(name, pagination)
        }
        return topics.map { topicViewMapper.map(it) }
    }

    fun findById(id: Long): TopicView {
        val topic = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topicViewMapper.map(topic)
    }

    fun findByIdModel(id: Long): Topic = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }

    fun create(dto: NewTopicForm): TopicView {
        val topic = newTopicFormMapper.map(dto)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        topic.title = form.title
        topic.message = form.message
        topic.updatedAt = LocalDateTime.now()
        // TODO need to update
        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) = repository.deleteById(id)

    fun report(): List<TopicByCategoryDto> = repository.report()

    fun findNotAnswered(): List<TopicView> {
        val topics = repository.findNotAnswered()
        return topics.map { topicViewMapper.map(it) }
    }
}