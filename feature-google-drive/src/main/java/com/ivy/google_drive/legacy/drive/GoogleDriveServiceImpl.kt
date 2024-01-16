package com.ivy.google_drive.legacy.drive

import com.google.api.client.http.FileContent
import com.google.api.services.drive.Drive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File


class GoogleDriveServiceImpl(
    private val drive: Drive,
) : GoogleDriveService {

    override suspend fun createFile(
        file: File,
        fileName: String,
        parentFolders: List<String>?,
        mimeType: DriveMimeType,
    ): String? {
        return withContext(Dispatchers.IO) {
            try {
                val metadataFile = GoogleDriveFile().apply {
                    name = fileName
                    parentFolders?.let {
                        setParents(it)
                    }
                }
                val fileContent = FileContent(mimeType.value, file)
                drive.files()
                    .create(metadataFile, fileContent)
                    .setFields("id")
                    .execute().id
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun createFolder(
        folderName: String,
        parentFolders: List<String>?
    ): String? {
        return withContext(Dispatchers.IO) {
            try {
                val metadataFolder = GoogleDriveFile().apply {
                    name = folderName
                    parentFolders?.let {
                        setParents(it)
                    }
                    mimeType = "application/vnd.google-apps.folder"
                }
                drive.files().create(metadataFolder).setFields("id").execute().id
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun searchForFile(
        mimeType: DriveMimeType,
        name: String
    ): GoogleDriveFile? {
        return withContext(Dispatchers.IO) {
            try {
                drive.files()
                    .list()
                    .setQ("mimeType='${mimeType.value}'")
                    .execute()
                    .files
                    .firstOrNull { it.name == name }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun searchForFiles(query: String): List<GoogleDriveFile> {
        return withContext(Dispatchers.IO) {
            try {
                drive.files()
                    .list()
                    .setQ(query)
                    .execute()
                    .files
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }


    override suspend fun deleteFile(
        fileId: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                drive.files().delete(fileId).execute()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}