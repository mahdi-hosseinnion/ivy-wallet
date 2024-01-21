package com.ivy.settings

import android.content.Intent
import java.time.LocalDateTime

sealed interface GoogleDriveBackupState {

    data object CheckingUserAuthState : GoogleDriveBackupState

    data class SignedOut(
        val signedUpIntent: Intent,
    ) : GoogleDriveBackupState

    data object TryingToSignUp : GoogleDriveBackupState

    data class SignedUp(
        val email: String?,
        val lastBackupDate: LocalDateTime = LocalDateTime.now() //TODO REMOVE THIS
    ) : GoogleDriveBackupState

}

val GoogleDriveBackupState.backupIsEnabled: Boolean
    get() {
        return this is GoogleDriveBackupState.SignedUp
    }