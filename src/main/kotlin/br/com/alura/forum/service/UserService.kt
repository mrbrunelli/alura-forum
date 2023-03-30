package br.com.alura.forum.service

import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService {
    private var users: List<User> = listOf()

    init {
        users = listOf(
            User(
                id = 1,
                name = "Matheus",
                email = "matheus@email.com.br"
            ),
            User(
                id = 2,
                name = "Brunelli",
                email = "brunelli@email.com.br"
            )
        )
    }

    fun findById(id: Long): User? = users.find { it.id == id }
}
