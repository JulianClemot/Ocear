package tech.ippon.ocear.reader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import tech.ippon.ocear.common.ArKeyword
import tech.ippon.ocear.ui.theme.BookVisualizerTheme
import tech.ippon.ocear.ui.theme.leaguesUnderTheSeaColors

@Composable
fun CameraReaderScreen(
    modifier: Modifier = Modifier,
    cameraViewModel: CameraViewModel = hiltViewModel(),
    chosenColor : Color,
    goToArScreen: (keywords: List<ArKeyword>, chosenColor: Color) -> Unit,
) {
    cameraViewModel.chosenColor = chosenColor
    CameraPermissionCheck {
        CameraCapture(
            modifier = modifier,
            onImageTaken = cameraViewModel.onImageTaken(goToArScreen),
            colorChosen = chosenColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CameraReaderScreenPreview() {
    BookVisualizerTheme {
        CameraReaderScreen(goToArScreen = {_, _ -> }, chosenColor = leaguesUnderTheSeaColors.buttonColor)
    }
}