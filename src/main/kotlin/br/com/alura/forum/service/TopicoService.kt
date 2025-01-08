package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.dto.UpdateTopicoForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "asdasdasdasd"
) {

    fun getTopicos(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map { topico ->
            topicoViewMapper.map(topico)
        }
    }

    fun getTopicoById(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow { NotFoundException("O topico ${id} não foi encontrado!") }

        return topicoViewMapper.map(topico)
    }

    fun createTopico(form: NewTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)

        repository.save(topico)

        return topicoViewMapper.map(topico)
    }

    fun updateTopico(form: UpdateTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }

        topico.title = form.title
        topico.message = form.message
        topico.updatedAt = LocalDate.now()

        return topicoViewMapper.map(topico)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}