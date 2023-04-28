package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Author
import br.com.alura.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository): UserDetailsService {
    fun findById(id: Long): Author = repository.getReferenceById(id) ?: throw NotFoundException("author not found")

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(user)
    }
}
