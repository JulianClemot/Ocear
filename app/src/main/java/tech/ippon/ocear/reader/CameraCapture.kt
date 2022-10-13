package tech.ippon.ocear.reader

import androidx.camera.core.AspectRatio.RATIO_16_9
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun CameraCapture(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    imageCaptureUseCase: ImageCapture = ImageCapture.Builder()
        .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
        .setTargetAspectRatio(RATIO_16_9).build(),
    onImageTaken: (InputImage) -> Unit,
    colorChosen: Color,
) {
    Box(modifier = modifier) {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }

        val coroutineScope = rememberCoroutineScope()

        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            onUseCase = { previewUseCase = it }
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    onImageTaken(imageCaptureUseCase.takePicture(context.executor))
                }
            },
            colors = ButtonDefaults.buttonColors(colorChosen),
            modifier = Modifier
                .padding(16.dp)
                .size(72.dp)
                .align(Alignment.BottomCenter),
        ) {
            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = "Camera",
                tint = Color.White,
            )
        }
        LaunchedEffect(previewUseCase) {
            val cameraProvider = context.getCameraProvider()
            try {
                // Must unbind the use-cases before rebinding them.
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    previewUseCase,
                    imageCaptureUseCase
                )
            } catch (ex: Exception) {
                Timber.e("Failed to bind camera use cases", ex)
            }
        }
    }
}