package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
//@Table(name = "topico")
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
    @JoinColumn(name = "created_by")
    val createdBy: Usuario,
    var updatedAt: LocalDate? = null
)