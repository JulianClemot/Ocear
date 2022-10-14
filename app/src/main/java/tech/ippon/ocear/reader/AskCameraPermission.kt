package tech.ippon.ocear.reader

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import tech.ippon.ocear.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionCheck(
    colorChosen: Color,
    content: @Composable () -> Unit,
) {
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.CAMERA)
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    permissionState.launchPermissionRequest()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textToShow = if (permissionState.status.shouldShowRationale) {
                    stringResource(id = R.string.please_grant_permission)
                } else {
                    stringResource(id = R.string.camera_required)
                }
                Text(textAlign = TextAlign.Center, text = textToShow)
                Button(
                    modifier = Modifier.padding(top = 10.dp),
                    onClick = { permissionState.launchPermissionRequest() },
                    colors = ButtonDefaults.buttonColors(colorChosen)
                ) {
                    Text(stringResource(id = R.string.ask_permission), color = Color.White)
                }
            }
        }
    }
}