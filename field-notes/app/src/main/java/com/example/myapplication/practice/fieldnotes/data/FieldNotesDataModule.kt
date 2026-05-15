package com.example.myapplication.practice.fieldnotes.data

import android.content.Context
import androidx.room.Room
import com.example.myapplication.practice.fieldnotes.database.FieldNotesDao
import com.example.myapplication.practice.fieldnotes.database.FieldNotesDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FieldNotesRepositoryModule {
    @Binds
    abstract fun bindFieldNotesRepository(
        repository: RoomFieldNotesRepository,
    ): FieldNotesRepository
}

@Module
@InstallIn(SingletonComponent::class)
object FieldNotesDatabaseModule {
    @Provides
    @Singleton
    fun provideFieldNotesDatabase(
        @ApplicationContext context: Context,
    ): FieldNotesDatabase =
        Room.databaseBuilder(
            context,
            FieldNotesDatabase::class.java,
            "field-notes.db",
        ).build()

    @Provides
    fun provideFieldNotesDao(database: FieldNotesDatabase): FieldNotesDao =
        database.fieldNotesDao()
}
