package br.com.alura.forum.controller

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.service.AnswerService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topics/{id}/answers")
class AnswerController(private val service: AnswerService) {

    @GetMapping
    fun listByTopicId(@PathVariable id: Long): List<AnswerView> = service.listByTopicId(id)

    @PostMapping
    fun create(@PathVariable id: Long, @RequestBody @Valid dto: NewAnswerForm) {
        service.create(dto, id)
    }
}