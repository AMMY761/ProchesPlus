package com.example.prochesplus.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val role: UserRole = UserRole.SENIOR,
    val familyCode: String = "",
    val profileImageUrl: String = "",
    val dateOfBirth: String = "",
    val address: String = "",
    val createdAt: Long = 0L
)

enum class UserRole {
    SENIOR,
    CAREGIVER
}
