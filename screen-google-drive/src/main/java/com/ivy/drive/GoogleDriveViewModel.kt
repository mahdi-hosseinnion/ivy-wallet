package com.ivy.drive

import androidx.compose.runtime.Composable
import com.ivy.domain.ComposeViewModel

class GoogleDriveViewModel    : ComposeViewModel<GoogleDriveScreenState, GoogleDriveScreenEvent>() {
    @Composable
    override fun uiState(): GoogleDriveScreenState {
       return GoogleDriveScreenState()
    }

    override fun onEvent(event: GoogleDriveScreenEvent) {

    }
}