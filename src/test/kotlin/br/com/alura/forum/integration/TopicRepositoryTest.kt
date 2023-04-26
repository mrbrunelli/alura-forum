package br.com.alura.forum.integration

import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {
    @Autowired
    private lateinit var repository: TopicRepository

    private val topic = TopicTest.build()

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.33").apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpassword")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
        }
    }

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