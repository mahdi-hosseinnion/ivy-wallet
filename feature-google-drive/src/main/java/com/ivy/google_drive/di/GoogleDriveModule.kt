package com.ivy.google_drive.di


import com.ivy.google_drive.DefaultGoogleDriveBackupRepository
import com.ivy.google_drive.GoogleDriveBackupRepository
import com.ivy.google_drive.auth.DefaultGoogleDriveAuthDataSource
import com.ivy.google_drive.auth.GoogleDriveAuthDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GoogleDriveModule {

    @Binds
    abstract fun googleDriveBackupRepository(concreteImpl: DefaultGoogleDriveBackupRepository): GoogleDriveBackupRepository

    @Binds
    abstract fun googleDriveAuthDataSource(concreteImpl: DefaultGoogleDriveAuthDataSource): GoogleDriveAuthDataSource


}