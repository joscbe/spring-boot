package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))
    val slot = slot<Topico>()

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }
    val topicoViewMapper: TopicoViewMapper = mockk{
        every { map(capture(slot)) } returns TopicoViewTest.build()
    }
    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.getTopicos("Kotlin", paginacao)

        val topico = TopicoTest.build()

        assertThat(slot.captured.title).isEqualTo(topico.title)
        assertThat(slot.captured.message).isEqualTo(topico.message)
        assertThat(slot.captured.status).isEqualTo(topico.status)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`() {
        topicoService.getTopicos(null, paginacao)

        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
    }

    @Test
    fun `deve listar not found exception quando topico nao for achado`() {
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.getTopicoById(1)
        }

        assertThat(atual.message).isEqualTo("Topico não encontrado!")
    }
}