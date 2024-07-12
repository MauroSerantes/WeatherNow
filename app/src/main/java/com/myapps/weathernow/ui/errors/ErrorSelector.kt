package com.myapps.weathernow.ui.errors

import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.myapps.weathernow.domain.utils.Errors


@Composable
fun ErrorSelector(
    errorMessage: String,
    errorDescription: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    when (errorMessage) {
        Errors.NO_PERMISSION_GRANTED -> {

            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                android.net.Uri.fromParts("package", context.packageName, null)
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            ErrorCard(
                errorMessage = errorMessage,
                errorDescription = errorDescription,
                onRefresh = { onRefresh() },
                onAction = {
                    startActivity(context, intent, null)
                },
                actionName = "Permission",
                modifier = modifier
            )
        }

        Errors.NO_GPS_ACTIVATED -> {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            ErrorCard(
                errorMessage = errorMessage,
                errorDescription = errorDescription,
                onRefresh = { onRefresh() },
                onAction = {
                    startActivity(context, intent, null)
                },
                actionName = "Gps Activation",
                modifier = modifier
            )
        }

        else -> {
            ErrorCard(
                errorMessage = errorMessage,
                errorDescription = errorDescription,
                onRefresh = { onRefresh() },
                onAction = { },
                actionName = null,
                modifier = modifier
            )
        }
    }

}


