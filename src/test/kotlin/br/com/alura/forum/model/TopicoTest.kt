package br.com.alura.forum.model

object TopicoTest {

    fun build() = Topico(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo Kotlin basico",
        status = TopicoStatusEnum.NAO_RESPONDIDO,
        curso = CursoTest.build(),
        createdBy = UsuarioTest.build()
    )
}