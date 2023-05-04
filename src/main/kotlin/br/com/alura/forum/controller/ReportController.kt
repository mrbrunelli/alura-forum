package br.com.alura.forum.controller

import br.com.alura.forum.service.TopicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("reports")
class ReportController(private val topicService: TopicService) {
    @GetMapping
    fun topicReport(model: Model): String {
        model.addAttribute("topicsByCategory", topicService.report())
        return "report"
    }
}