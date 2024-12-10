package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.dto.UpdateTopicoForm
import br.com.alura.forum.service.TopicoService
import jakarta.validation.Valid
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

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun getTopicos(
        @RequestParam(required = false)
        nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC)
        paginacao: Pageable
    ): Page<TopicoView> {
        return service.getTopicos(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun getTopicoById(@PathVariable id: Long): TopicoView {
        return service.getTopicoById(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return service.relatorio()
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun createTopico(
        @RequestBody @Valid form: NewTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoView = service.createTopico(form)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    @Transactional
    @CacheEvict("topicos", allEntries = true)
    fun updateTopico(@RequestBody @Valid form: UpdateTopicoForm): ResponseEntity<TopicoView> {
        val topicoView = service.updateTopico(form)

        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deleteTopico(@PathVariable id: Long) {
        service.delete(id)
    }

}