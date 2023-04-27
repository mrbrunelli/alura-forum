package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.service.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {
    @GetMapping
    fun list(
        @RequestParam(required = false) name: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicView> = service.list(name, pagination)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView? = service.findById(id)

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid form: NewTopicForm, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topic = service.create(form)
        val uri = uriBuilder.path("/topics/${topic.id}").build().toUri()
        return ResponseEntity.created(uri).body(topic)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid form: UpdateTopicForm): ResponseEntity<TopicView> {
        val topic = service.update(form)
        return ResponseEntity.ok(topic)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) = service.delete(id)

    @GetMapping("/report")
    fun report() = service.report()

    @GetMapping("/not-answered")
    fun findNotAnswered() = service.findNotAnswered()
}