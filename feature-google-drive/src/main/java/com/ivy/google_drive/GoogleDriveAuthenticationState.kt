package com.ivy.google_drive

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class GoogleDriveAccount(
    val email: String?
)

fun GoogleSignInAccount.toGoogleDriveAccount(): GoogleDriveAccount = GoogleDriveAccount(this.email)

