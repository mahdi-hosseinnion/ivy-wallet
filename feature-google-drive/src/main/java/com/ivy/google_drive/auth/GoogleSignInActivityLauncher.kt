package com.ivy.google_drive.auth

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.lang.Exception

@Composable
fun createGoogleSignInActivityLauncher(
    onSuccessfullySignedIn: (GoogleSignInAccount) -> Unit,
    onSignedInFailed: (Exception?) -> Unit,
    onCancel: () -> Unit,
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode == RESULT_OK) {
                val intent = it.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                val taskResult = task.result
                if (task.isSuccessful && taskResult != null) {
                    onSuccessfullySignedIn(taskResult)
                } else {
                    onSignedInFailed(task.exception)
                }
            } else {
                onCancel()
            }
        }
    )
}
