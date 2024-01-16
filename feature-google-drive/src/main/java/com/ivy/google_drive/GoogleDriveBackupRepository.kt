package com.ivy.google_drive

interface GoogleDriveBackupRepository {
    suspend fun backupNow()
}