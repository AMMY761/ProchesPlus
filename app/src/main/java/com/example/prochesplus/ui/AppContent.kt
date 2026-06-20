package com.example.prochesplus.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Material3
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.prochesplus.ui.theme.ProchesPlus
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun AppContent() {
    ProchesPlus {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            MainNavigation()
        }
    }
}
