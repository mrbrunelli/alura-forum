package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.NewTopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.model.TopicViewTest
import br.com.alura.forum.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {
    private val topics = PageImpl(listOf(TopicTest.build()))
    private val pagination: Pageable = mockk()
    private val topicRepository: TopicRepository = mockk {
        every { findByCourseName(any(), any()) } returns topics
        every { findAll(pagination) } returns topics
    }
    private val topicViewMapper: TopicViewMapper = mockk {
        // I can use slot + capture to retrieve access for the method's param
        // val slot = slot<Topic>()
        // map(capture(slot))
        every { map(any()) } returns TopicViewTest.build()
    }
    private val topicFormMapper: NewTopicFormMapper = mockk()
    private val topicService = TopicService(topicRepository, topicViewMapper, topicFormMapper)

    @Test
    fun `should list topic by course name`() {
        topicService.list("kotlin avançado", pagination)
        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pagination) }
    }

    @Test
    fun `should list all topics when name is null`() {
        topicService.list(null, pagination)
        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 1) { topicRepository.findAll(pagination) }
    }

    @Test
    fun `should throws NotFoundException when the topic is not found`() {
        every { topicRepository.findById(any()) } returns Optional.empty()
        val exception = assertThrows<NotFoundException> {
            topicService.findById(1)
        }
        assertThat(exception.message).isEqualTo("topic not found")
    }
}