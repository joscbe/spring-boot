package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
): Mapper<NewTopicoForm, Topico> {

    override fun map(t: NewTopicoForm): Topico {
        return Topico(
            title = t.title,
            message = t.message,
            curso = cursoService.getCurso(t.cursoId),
            createdBy = usuarioService.getUsuarioById(t.usuarioId)
        )
    }

}
