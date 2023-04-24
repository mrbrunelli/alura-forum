package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class NewTopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService,
) : Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        val course = courseService.findById(t.courseId) ?: throw Error("Course not found")
        val user = userService.findById(t.authorId) ?: throw Error("User not found")
        return Topic(
            title = t.title,
            message = t.message,
            course = course,
            author = user,
        )
    }
}