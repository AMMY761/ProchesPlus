package com.example.prochesplus.data.repository

import com.example.prochesplus.data.local.dao.ContactDao
import com.example.prochesplus.data.local.entity.ContactEntity
import com.example.prochesplus.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun insertContact(contact: Contact) {
        val entity = contact.toEntity()
        contactDao.insertContact(entity)
    }

    suspend fun updateContact(contact: Contact) {
        val entity = contact.toEntity()
        contactDao.updateContact(entity)
    }

    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact.toEntity())
    }

    fun getContactById(id: String): Flow<Contact?> {
        return contactDao.getContactById(id).map { it?.toModel() }
    }

    fun getContactsByUserId(userId: String): Flow<List<Contact>> {
        return contactDao.getContactsByUserId(userId).map { contacts ->
            contacts.map { it.toModel() }
        }
    }

    fun getFavoriteContacts(userId: String): Flow<List<Contact>> {
        return contactDao.getFavoriteContactsByUserId(userId).map { contacts ->
            contacts.map { it.toModel() }
        }
    }

    private fun Contact.toEntity(): ContactEntity {
        return ContactEntity(
            id = id,
            name = name,
            phone = phone,
            email = email,
            relationship = relationship,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
            userId = userId,
            createdAt = createdAt
        )
    }

    private fun ContactEntity.toModel(): Contact {
        return Contact(
            id = id,
            name = name,
            phone = phone,
            email = email,
            relationship = relationship,
            imageUrl = imageUrl,
            isFavorite = isFavorite,
            userId = userId,
            createdAt = createdAt
        )
    }
}
