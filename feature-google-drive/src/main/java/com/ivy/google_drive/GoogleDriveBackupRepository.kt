package com.ivy.google_drive

import android.content.Intent
import arrow.core.Either

interface GoogleDriveBackupRepository {

    fun getUserAuthenticationState(): Either<Intent, GoogleDriveAccount>

    suspend fun backupNow()
}