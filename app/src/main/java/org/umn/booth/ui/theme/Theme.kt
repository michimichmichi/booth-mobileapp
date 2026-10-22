package org.umn.booth.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = BoothPrimary,
    onPrimary = Color.White,
    primaryContainer = BoothBadgeBg,
    onPrimaryContainer = BoothBadgeText,
    secondary = BoothSecondary,
    onSecondary = Color.White,
    tertiary = BoothTertiary,
    onTertiary = Color.White,
    background = BoothBackground,
    onBackground = BoothTextPrimary,
    surface = BoothSurface,
    onSurface = BoothTextPrimary,
    surfaceVariant = BoothSurfaceVariant,
    onSurfaceVariant = BoothTextSecondary,
    outline = BoothOutline,
    error = BoothError,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = BoothPrimaryDarkTheme,
    onPrimary = BoothPrimaryDark,
    primaryContainer = BoothPrimaryDark,
    onPrimaryContainer = BoothPrimaryDarkTheme,
    secondary = BoothSecondary,
    onSecondary = Color.White,
    tertiary = BoothTertiary,
    onTertiary = Color.White,
    background = BoothBackgroundDark,
    onBackground = BoothTextPrimaryDark,
    surface = BoothSurfaceDark,
    onSurface = BoothTextPrimaryDark,
    surfaceVariant = BoothSurfaceVariantDark,
    onSurfaceVariant = BoothTextSecondaryDark,
    outline = Color(0xFF2E3D59),
    error = BoothError,
    onError = Color.White
)

@Composable
fun BOOTHTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.let {
                it.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
