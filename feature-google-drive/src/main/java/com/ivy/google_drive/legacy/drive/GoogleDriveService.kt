package com.ivy.google_drive.legacy.drive

import java.io.File

interface GoogleDriveService {

    suspend fun createFile(
        file: File,
        fileName: String,
        parentFolders: List<String>?,
        mimeType: DriveMimeType,
    ): String?

    suspend fun createFolder(
        folderName: String,
        parentFolders: List<String>? = null
    ): String?

    suspend fun searchForFile(
        mimeType: DriveMimeType,
        name: String
    ): GoogleDriveFile?

    suspend fun searchForFiles(
        query: String
    ): List<GoogleDriveFile>

    suspend fun deleteFile(
        fileId: String
    ): Boolean

}