package com.ivy.google_drive

import android.content.Context
import com.ivy.domain.IvyBackupCreator
import com.ivy.google_drive.Constants.IVY_BACKUP_FILE_NAME_PREFIX
import com.ivy.google_drive.auth.GoogleDriveAuthDataSource
import com.ivy.google_drive.drive.DefaultGoogleDriveDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DefaultGoogleDriveBackupRepository
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val googleDriveAuthService: GoogleDriveAuthDataSource,
    private val backupFileGenerator: IvyBackupCreator,
) : GoogleDriveBackupRepository {
    override suspend fun backupNow() {
        val googleDrive = googleDriveAuthService.authenticate()
        val googleDriveService = DefaultGoogleDriveDataSource(googleDrive)
        val backupJson = backupFileGenerator.createBackupJson()
        val date = Date().let {  SimpleDateFormat("dd-MM-yyyy H:m:s Z").format(it)  }
        val backupFile = File(context.filesDir, "${IVY_BACKUP_FILE_NAME_PREFIX}-$date.json").apply {
            writeText(backupJson)
        }
        googleDriveService.saveFile(backupFile)
    }
}