package com.ivy.settings

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ivy.google_drive.auth.GoogleSignInActivityLauncher

@Composable
fun GoogleDriveBackup(
    state: GoogleDriveBackupState,
    onUserSignedUp: () -> Unit,
    onStartSigningUserIn: () -> Unit,
    onUserWasUnableToSignup: (String?) -> Unit,
    createBackupNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    GoogleSignInActivityLauncher(
        onResult = { onUserSignedUp() },
        onError = { ex -> onUserWasUnableToSignup(ex?.message) },
        onCancel = { /*Do nothing*/ }
    ) { launcher ->
        GoogleDriveBackupButton(
            state = state,
            signUserIn = { intent ->
                onStartSigningUserIn()
                launcher.launch(intent)
            },
            createBackupNow = createBackupNow,
            modifier = modifier,
        )
    }
}

@Composable
private fun GoogleDriveBackupButton(
    state: GoogleDriveBackupState,
    signUserIn: (Intent) -> Unit,
    createBackupNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            when (state) {
                is GoogleDriveBackupState.SignedUp -> {
                    createBackupNow()
                }

                is GoogleDriveBackupState.SignedOut -> {
                    signUserIn(state.signedUpIntent)
                }

                GoogleDriveBackupState.CheckingUserAuthState -> {}
                GoogleDriveBackupState.SigningUp -> {}
            }
        }
    ) {
        when (state) {
            is GoogleDriveBackupState.SignedUp -> Text("SignedUp")
            is GoogleDriveBackupState.SignedOut -> Text("SignedOut")
            GoogleDriveBackupState.CheckingUserAuthState -> Text("CheckingUserAuthState")
            GoogleDriveBackupState.SigningUp -> Text("SigningUp")
        }
    }
}

