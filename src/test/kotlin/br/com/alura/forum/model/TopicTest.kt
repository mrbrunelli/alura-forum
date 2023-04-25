package br.com.alura.forum.model

object TopicTest {
    fun build() = Topic(
        id = 1,
        title = "Kotlin básico",
        message = "Aprendendo kotlin básico",
        course = CourseTest.build(),
        author = AuthorTest.build()
    )
}