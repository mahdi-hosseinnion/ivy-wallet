package com.ivy.google_drive.drive

import com.google.api.services.drive.Drive
import com.ivy.google_drive.Constants.IVY_FOLDER_NAME
import com.ivy.google_drive.legacy.drive.DriveMimeType
import com.ivy.google_drive.legacy.drive.GoogleDriveServiceImpl
import java.io.File

class DefaultGoogleDriveDataSource(private val drive: Drive) : GoogleDriveDataSource {
    private val googleDriveService = GoogleDriveServiceImpl(drive)
    override suspend fun saveFile(file: File): String? {
        val ivyFolder = getOrCreateIvyFolder()
        return googleDriveService.createFile(
            file = file,
            fileName = file.name,
            parentFolders = listOf(ivyFolder),
            mimeType = DriveMimeType.JSON
        )
    }

    // Note : If the user puts the Ivy folder in the trash (on google drive), this function will consider that the file is still existed
    // And it won't create another one
    private suspend fun getOrCreateIvyFolder(): String {
        val folder = googleDriveService.searchForFile(DriveMimeType.DRIVE_FOLDER, IVY_FOLDER_NAME)
        return if (folder == null) {
            createFolder(IVY_FOLDER_NAME)
        } else {
            folder.id
        }
    }
    private suspend fun createFolder(
        name: String,
    ): String {
        return googleDriveService.createFolder(name)
            ?: throw Exception("Cannot create a folder")
    }

}