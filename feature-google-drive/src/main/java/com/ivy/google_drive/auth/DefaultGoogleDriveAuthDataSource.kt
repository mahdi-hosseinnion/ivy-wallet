package com.ivy.google_drive.auth

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultGoogleDriveAuthDataSource
@Inject
constructor(
    @ApplicationContext val context: Context
) : GoogleDriveAuthDataSource {

    override fun authenticate(): Drive {
        val googleAccount = GoogleSignIn.getLastSignedInAccount(context)
        val credential =
            GoogleAccountCredential.usingOAuth2(context, listOf(DriveScopes.DRIVE_FILE))
        credential.selectedAccount = googleAccount?.account
        return Drive.Builder(
            AndroidHttp.newCompatibleTransport(),
            JacksonFactory.getDefaultInstance(),
            credential
        ).setApplicationName("Ivy Wallet")
            .build()
    }

}