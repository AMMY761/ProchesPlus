package com.example.prochesplus.model

data class Contact(
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val relationship: String = "",
    val imageUrl: String = "",
    val isFavorite: Boolean = false,
    val userId: String = "",
    val createdAt: Long = 0L
)
