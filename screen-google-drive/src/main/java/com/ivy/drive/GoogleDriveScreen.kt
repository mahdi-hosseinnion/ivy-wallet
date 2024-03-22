package com.ivy.drive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ivy.navigation.IvyPreview
import com.ivy.navigation.screenScopedViewModel

@Composable
fun BoxWithConstraintsScope.GoogleDriveScreen() {
    val viewModel: GoogleDriveViewModel = screenScopedViewModel()
    val state = viewModel.uiState()
    GoogleDriveUI(
        state = state,
        onEventHandler = viewModel::onEvent
    )
}

@Composable
private fun GoogleDriveUI(
    state: GoogleDriveScreenState,
    onEventHandler: (GoogleDriveScreenEvent) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        Text(text = "Google drive screen")
    }

}

@Preview
@Composable
private fun Preview() {
    IvyPreview {
        GoogleDriveUI(
            state = GoogleDriveScreenState()
        ) {}
    }
}