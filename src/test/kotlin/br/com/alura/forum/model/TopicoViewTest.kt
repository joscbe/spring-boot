package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicoView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo Kotlin basico",
        status = TopicoStatusEnum.NAO_RESPONDIDO,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDate.now()
    )
}