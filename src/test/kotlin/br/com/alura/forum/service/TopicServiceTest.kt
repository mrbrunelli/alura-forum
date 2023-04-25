package br.com.alura.forum.service

import br.com.alura.forum.mapper.NewTopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.model.TopicViewTest
import br.com.alura.forum.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicServiceTest {
    val topics = PageImpl(listOf(TopicTest.build()))
    val pagination: Pageable = mockk()
    val topicRepository: TopicRepository = mockk {
        every {
            findByCourseName(any(), any())
        } returns topics
    }
    val topicViewMapper: TopicViewMapper = mockk()
    val topicFormMapper: NewTopicFormMapper = mockk()
    val topicService = TopicService(topicRepository, topicViewMapper, topicFormMapper)

    @Test
    fun `should list topic by course name`() {
        every { topicViewMapper.map(any()) } returns TopicViewTest.build()
        topicService.list("kotlin avançado", pagination)
        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pagination) }
    }
}