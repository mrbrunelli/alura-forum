package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reports")
class ReportController(private val topicService: TopicService) {
    @GetMapping
    fun topicReport(): List<TopicByCategoryDto> {
        return topicService.report()
    }
}