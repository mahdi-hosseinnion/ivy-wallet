package com.ivy.settings.di

import com.ivy.domain.IvyBackupCreator
import com.ivy.legacy.domain.deprecated.logic.zip.BackupLogic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SettingsBindingModule {

    @Provides
    fun provideIvyBackupCreator(
        backupLogic: BackupLogic,
    ): IvyBackupCreator = object : IvyBackupCreator {
        override suspend fun createBackupJson(): String {
            return backupLogic.generateJsonBackup()
        }
    }

}