package br.com.alura.forum.controller

import br.com.alura.forum.config.DatabaseContainerConfig
import br.com.alura.forum.config.JWTUtil
import br.com.alura.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest : DatabaseContainerConfig() {
    @Autowired
    private lateinit var webAppContext: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc
    private var token: String? = null

    companion object {
        private const val RESOURCE = "/topics/"
        private const val RESOURCE_ID = RESOURCE.plus("%s")
    }

    private fun generateToken(): String {
        val authorities = mutableListOf(Role(1, "READ_WRITE"))
        return jwtUtil.generateToken("brunelli@email.com", authorities)
    }

    @BeforeEach
    fun setup() {
        token = generateToken()
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
            .apply<DefaultMockMvcBuilder?>(SecurityMockMvcConfigurers.springSecurity())
            .build()
    }

    @Test
    fun `should return status code 4xx when the token is not provided`() {
        mockMvc.get(RESOURCE).andExpect {
            status { is4xxClientError() }
        }
    }

    @Test
    fun `should return status code 200 when the valid token is provided`() {
        mockMvc.get(RESOURCE) {
            headers { token?.let { setBearerAuth(it) } }
        }.andExpect {
            status { is2xxSuccessful() }
        }
    }

    @Test
    fun `should return status code 200 when the topic's id and a valid token is provided`() {
        mockMvc.get(RESOURCE_ID.format("1")) {
            headers { token?.let { setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }
}