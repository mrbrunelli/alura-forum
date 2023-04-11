package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.mapper.NewTopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicViewMapper: TopicViewMapper,
    private val newTopicFormMapper: NewTopicFormMapper
) {
    private var topics: MutableList<Topic> = mutableListOf()

    fun list(): List<TopicView> = topics.map { topicViewMapper.map(it) }

    fun findById(id: Long): TopicView {
        val topic = topics.find { id == it.id } ?: throw Error("Topic not found")
        return topicViewMapper.map(topic)
    }

    fun findByIdModel(id: Long): Topic = topics.find { id == it.id } ?: throw Error("Topic not found")

    fun create(dto: NewTopicForm): TopicView {
        val topic = newTopicFormMapper.map(dto)
        topic.id = topics.size.toLong() + 1
        topics.add(topic)
        return topicViewMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic = topics.find { it.id == form.id} ?: throw Error("Topic not found")
        val newTopic = Topic(
            id = form.id,
            title = form.title,
            message = form.message,
            author = topic.author,
            course = topic.course,
            answers = topic.answers,
            status = topic.status,
            createdAt = topic.createdAt
        )
        topics = topics.minus(topic).plus(newTopic).toMutableList()
        return topicViewMapper.map(newTopic)
    }

    fun delete(id: Long) {
        val topic = topics.find {it.id == id} ?: throw Error("Topic not found")
        topics = topics.minus(topic).toMutableList()
    }
}