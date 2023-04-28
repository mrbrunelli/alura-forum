package br.com.alura.forum.dto

import java.io.Serializable

data class AuthorView(
    val id: Long?,
    val name: String,
    val email: String
): Serializable
