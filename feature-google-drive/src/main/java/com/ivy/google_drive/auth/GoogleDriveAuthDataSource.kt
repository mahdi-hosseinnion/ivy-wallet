package com.ivy.google_drive.auth

import com.google.api.services.drive.Drive

interface GoogleDriveAuthDataSource {
    fun authenticate(): Drive
}