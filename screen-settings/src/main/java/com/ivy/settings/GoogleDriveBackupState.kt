package com.ivy.settings

import android.content.Intent

sealed interface GoogleDriveBackupState {

    data object CheckingUserAuthState : GoogleDriveBackupState

    data class SignedOut(
        val signedUpIntent: Intent,
    ) : GoogleDriveBackupState

    data object SigningUp : GoogleDriveBackupState

    data class SignedUp(
        val email: String?,
    ) : GoogleDriveBackupState

}