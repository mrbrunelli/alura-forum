package br.com.alura.forum.controller

import br.com.alura.forum.dto.AnswerView
import br.com.alura.forum.model.Answer
import br.com.alura.forum.service.AnswerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics/{id}/answers")
class AnswerController(private val service: AnswerService) {

    @GetMapping
    fun listByTopicId(@PathVariable id: Long): List<AnswerView> = service.listByTopicId(id)
}