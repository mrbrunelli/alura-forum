package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewAnswerForm
import br.com.alura.forum.service.AnswerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearerAuth")
class AnswerController(private val service: AnswerService) {
    @PostMapping
    @Transactional
    fun create(@RequestBody @Valid dto: NewAnswerForm) = service.create(dto)
}