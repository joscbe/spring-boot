package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewTopicoForm(
    @field:NotEmpty(message = "Titulo não pode ser em branco")
    @field:Size(min = 5, max = 50, message = "Titulo deve ter entre 5 e 50 caracteres")
    val title: String,

    @field:NotEmpty
    val message: String,

    @field:NotNull
    val cursoId: Long,

    @field:NotNull
    val usuarioId: Long
)