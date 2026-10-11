package com.kali.mobile.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val KaliDarkColorScheme = darkColorScheme(
    primary = Color(0xFF9BF5C8),
    secondary = Color(0xFF3DDC97),
    tertiary = Color(0xFF6AE0B5),
    background = Color(0xFF09160F),
    surface = Color(0xFF112B22),
    onPrimary = Color(0xFF071A12),
    onSecondary = Color(0xFF071A12),
    onBackground = Color(0xFFEAFEF5),
    onSurface = Color(0xFFEAFEF5)
)

@Composable
fun KaliMobileTheme(content: @Composable () -> Unit) {
    androidx.compose.material3.MaterialTheme(
        colorScheme = KaliDarkColorScheme,
        typography = Typography,
        content = content
    )
}
