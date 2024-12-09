package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
//@Table(name = "resposta")
data class Resposta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val message: String,
    val solucao: Boolean,
    @ManyToOne
    val topico: Topico,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "created_by")
    val createdBy: Usuario
)
