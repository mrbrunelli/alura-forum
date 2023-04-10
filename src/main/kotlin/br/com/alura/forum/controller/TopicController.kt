package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {
    @GetMapping
    fun list(): List<TopicView> = service.list()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView? = service.findById(id)

    @PostMapping
    fun create(@RequestBody @Valid form: NewTopicForm) = service.create(form)

    @PutMapping
    fun update(@RequestBody @Valid form: UpdateTopicForm) = service.update(form)
}