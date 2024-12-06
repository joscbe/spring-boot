package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    @ManyToOne
    val curso: Curso,
    @Enumerated(value = EnumType.STRING)
    val status: TopicoStatusEnum = TopicoStatusEnum.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico")
    val resposta: List<Resposta> = ArrayList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val createdBy: Usuario
)