package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService {
    private var courses: List<Course> = listOf()

    init {
        courses = listOf(
            Course(
                id = 1,
                name = "kotlin",
                category = "programação"
            ),
            Course(
                id = 2,
                name = "java",
                category = "programação"
            ),
            Course(
                id = 3,
                name = "typescript",
                category = "programação"
            )
        )
    }

    fun findById(id: Long): Course? = courses.find { it.id == id }
}
