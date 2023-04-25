package br.com.alura.forum.model

object AuthorTest {
    fun build() = Author(
        id = 1,
        name = "Brunelli",
        email = "brunelli@email.com",
        password = "123456",
        role = mutableListOf(Role(id = 1, name = "READ_WRITE"))
    )
}