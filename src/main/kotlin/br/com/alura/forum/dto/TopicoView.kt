package br.com.alura.forum.dto

import br.com.alura.forum.model.TopicoStatusEnum
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicoStatusEnum,
    val createdAt: LocalDateTime
)
