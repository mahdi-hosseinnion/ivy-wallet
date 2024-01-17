package com.ivy.google_drive

import android.content.Context
import android.content.Intent
import arrow.core.Either
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.services.drive.DriveScopes
import com.ivy.domain.IvyBackupCreator
import com.ivy.google_drive.Constants.IVY_BACKUP_FILE_NAME_PREFIX
import com.ivy.google_drive.auth.GoogleDriveAuthDataSource
import com.ivy.google_drive.drive.DefaultGoogleDriveDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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


    override suspend fun backupNow(): Unit = withContext(Dispatchers.IO) {
        val googleDrive = googleDriveAuthService.authenticate()
        val googleDriveService = DefaultGoogleDriveDataSource(googleDrive)
        val backupJson = backupFileGenerator.createBackupJson()
        val date = Date().let { SimpleDateFormat("dd-MM-yyyy-H:m:sZ").format(it) }
        val fileName = "${IVY_BACKUP_FILE_NAME_PREFIX}-$date.json"
        val backupFile = File(context.filesDir, fileName).apply {
            writeText(backupJson)
        }
        googleDriveService.saveFile(backupFile)
    }

    override fun getUserAuthenticationState(): Either<Intent, GoogleDriveAccount> {
        val lastSignedInAccount: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(context)
        //TODO use GoogleDriveAuthDataSource
        return if (lastSignedInAccount == null) {
            return Either.Left(getGoogleSignInClient().signInIntent)
        } else {
            return Either.Right(lastSignedInAccount.toGoogleDriveAccount())
        }
    }

    private fun getGoogleSignInClient(): GoogleSignInClient {
        return GoogleSignIn.getClient(context, getGoogleSignInOptions())
    }

    private fun getGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder()
            .requestEmail()
            .requestProfile()
            .requestScopes(
                Scope(DriveScopes.DRIVE),
                Scope(DriveScopes.DRIVE_FILE),
                Scope(DriveScopes.DRIVE),//TODO remove extra
            )
            .build()
    }
}