package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.dto.UpdateTopicoForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "asdasdasdasd"
) {

    fun getTopicos(): List<TopicoView> {
        return repository.findAll().stream().map {
            topico -> topicoViewMapper.map(topico)
        }.collect(Collectors.toList())
    }

    fun getTopicoById(id: Long): TopicoView {
        val topico = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }

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

        return topicoViewMapper.map(topico)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}