package br.com.alura.forum.model

object UsuarioTest {

    fun build() = Usuario(
        id = 1,
        email = "teste.projex@gmail.com",
        password = "123456",
        role = listOf<Role>(
            Role(
                id = 1,
                nome = "LEITURA_ESCRITA"
            )
        ),
        nome = "Usuario Teste"
    )
}