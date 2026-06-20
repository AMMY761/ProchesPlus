package com.example.prochesplus.di

import android.content.Context
import androidx.room.Room
import com.example.prochesplus.data.local.ProchesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideProchesDatabase(
        @ApplicationContext context: Context
    ): ProchesDatabase {
        return Room.databaseBuilder(
            context,
            ProchesDatabase::class.java,
            "proches_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: ProchesDatabase) = database.userDao()

    @Singleton
    @Provides
    fun provideContactDao(database: ProchesDatabase) = database.contactDao()

    @Singleton
    @Provides
    fun provideMedicationDao(database: ProchesDatabase) = database.medicationDao()

    @Singleton
    @Provides
    fun provideAppointmentDao(database: ProchesDatabase) = database.appointmentDao()
}
