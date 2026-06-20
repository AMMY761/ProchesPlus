package com.example.prochesplus.data.repository

import com.example.prochesplus.data.local.dao.MedicationDao
import com.example.prochesplus.data.local.entity.MedicationEntity
import com.example.prochesplus.data.local.entity.MedicationLogEntity
import com.example.prochesplus.model.Medication
import com.example.prochesplus.model.MedicationLog
import com.example.prochesplus.model.MedicationStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MedicationRepository @Inject constructor(
    private val medicationDao: MedicationDao
) {
    suspend fun insertMedication(medication: Medication) {
        val entity = medication.toEntity()
        medicationDao.insertMedication(entity)
    }

    suspend fun updateMedication(medication: Medication) {
        val entity = medication.toEntity()
        medicationDao.updateMedication(entity)
    }

    suspend fun deleteMedication(medication: Medication) {
        medicationDao.deleteMedication(medication.toEntity())
    }

    fun getMedicationById(id: String): Flow<Medication?> {
        return medicationDao.getMedicationById(id).map { it?.toModel() }
    }

    fun getMedicationsByUserId(userId: String): Flow<List<Medication>> {
        return medicationDao.getMedicationsByUserId(userId).map { medications ->
            medications.map { it.toModel() }
        }
    }

    fun getActiveMedicationsByUserId(userId: String): Flow<List<Medication>> {
        return medicationDao.getActiveMedicationsByUserId(userId).map { medications ->
            medications.map { it.toModel() }
        }
    }

    suspend fun logMedicationTaken(medicationId: String, date: Long, time: String, status: MedicationStatus) {
        val log = MedicationLogEntity(
            id = System.currentTimeMillis().toString(),
            medicationId = medicationId,
            status = status.name,
            date = date,
            time = time,
            notes = ""
        )
        medicationDao.insertMedicationLog(log)
    }

    fun getMedicationLogs(medicationId: String): Flow<List<MedicationLog>> {
        return medicationDao.getMedicationLogsByMedicationId(medicationId).map { logs ->
            logs.map { it.toModel() }
        }
    }

    private fun Medication.toEntity(): MedicationEntity {
        return MedicationEntity(
            id = id,
            name = name,
            dosage = dosage,
            frequency = frequency,
            startDate = startDate,
            endDate = endDate,
            notes = notes,
            userId = userId,
            createdAt = createdAt
        )
    }

    private fun MedicationEntity.toModel(): Medication {
        return Medication(
            id = id,
            name = name,
            dosage = dosage,
            frequency = frequency,
            startDate = startDate,
            endDate = endDate,
            notes = notes,
            userId = userId,
            createdAt = createdAt
        )
    }

    private fun MedicationLogEntity.toModel(): MedicationLog {
        return MedicationLog(
            id = id,
            medicationId = medicationId,
            status = MedicationStatus.valueOf(status),
            date = date,
            time = time,
            notes = notes
        )
    }
}
