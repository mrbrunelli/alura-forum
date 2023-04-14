package br.com.alura.forum.service

import br.com.alura.forum.model.Author
import br.com.alura.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {
    fun findById(id: Long): Author = repository.getReferenceById(id)
}
