package com.android.secretly.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Secretly themed circular progress indicator. Wraps Material 3 [CircularProgressIndicator].
 *
 * @param modifier Modifier to be applied to the progress indicator.
 * @param color The color of the progress indicator. If null, will use
 * [MaterialTheme.colorScheme.primary].
 */
@Composable
fun SecretlyCircularProgress(
    modifier: Modifier = Modifier,
    color: Color? = null,
) {
    CircularProgressIndicator(
        modifier = modifier.size(
            SecretlyCircularProgressDefaults.Size
        ),
        color = color ?: MaterialTheme.colorScheme.primary
    )
}

/**
 * Secretly circular progress indicator default values.
 */
object SecretlyCircularProgressDefaults {
    /**
     * Default size of the progress indicator.
     */
    val Size = 48.dp
}