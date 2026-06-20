package com.example.prochesplus.data.repository

import com.example.prochesplus.data.local.dao.UserDao
import com.example.prochesplus.data.local.entity.UserEntity
import com.example.prochesplus.model.User
import com.example.prochesplus.model.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User) {
        val entity = user.toEntity()
        userDao.insertUser(entity)
    }

    suspend fun updateUser(user: User) {
        val entity = user.toEntity()
        userDao.updateUser(entity)
    }

    fun getUserById(id: String): Flow<User?> {
        return userDao.getUserById(id).map { it?.toModel() }
    }

    fun getUserByEmail(email: String): Flow<User?> {
        return userDao.getUserByEmail(email).map { it?.toModel() }
    }

    fun getUsersByFamilyCode(code: String): Flow<List<User>> {
        return userDao.getUsersByFamilyCode(code).map { users ->
            users.map { it.toModel() }
        }
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            email = email,
            phone = phone,
            role = role.name,
            familyCode = familyCode,
            profileImageUrl = profileImageUrl,
            dateOfBirth = dateOfBirth,
            address = address,
            createdAt = createdAt
        )
    }

    private fun UserEntity.toModel(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phone = phone,
            role = UserRole.valueOf(role),
            familyCode = familyCode,
            profileImageUrl = profileImageUrl,
            dateOfBirth = dateOfBirth,
            address = address,
            createdAt = createdAt
        )
    }
}
