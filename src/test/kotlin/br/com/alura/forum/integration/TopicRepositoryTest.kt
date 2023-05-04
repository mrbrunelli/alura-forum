package br.com.alura.forum.integration

import br.com.alura.forum.config.DatabaseContainerConfig
import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest : DatabaseContainerConfig() {
    @Autowired
    private lateinit var repository: TopicRepository
    private val topic = TopicTest.build()

    @Test
    fun `should generate a report`() {
        repository.save(topic)
        val report = repository.report()
        assertThat(report).isNotNull
        assertThat(report.first()).isExactlyInstanceOf(TopicByCategoryDto::class.java)
    }

    @Test
    fun `should list topic by course name`() {
        repository.save(topic)
        val topic = repository.findByCourseName(topic.course.name, PageRequest.of(0, 5))
        assertThat(topic).isNotNull
    }
}