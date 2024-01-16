package com.ivy.google_drive.drive

import java.io.File

interface GoogleDriveDataSource {
    suspend fun saveFile(file: File): String?
}

