package br.com.alura.forum.mapper

import br.com.alura.forum.dto.AuthorView
import br.com.alura.forum.model.Author
import org.springframework.stereotype.Component

@Component
class AuthorViewMapper : Mapper<Author, AuthorView> {
    override fun map(t: Author): AuthorView {
        return AuthorView(
            id = t.id,
            name = t.name,
            email = t.email
        )
    }
}
