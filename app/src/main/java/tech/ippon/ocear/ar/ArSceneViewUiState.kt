package tech.ippon.ocear.ar

import androidx.compose.ui.graphics.Color

sealed class ArSceneViewUiState {

    object Init : ArSceneViewUiState()
    object ModelSelected : ArSceneViewUiState()
    class ModelLoaded(val chosenColor: Color) : ArSceneViewUiState()
    object ModelAnchored : ArSceneViewUiState()
}
