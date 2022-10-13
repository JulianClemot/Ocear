package tech.ippon.ocear.ar

import androidx.compose.ui.graphics.Color
import tech.ippon.ocear.common.ArKeyword

sealed class KeywordsUiState {
    object Empty : KeywordsUiState()

    data class KeywordsRetrieved(val keywords: List<ArKeyword>, val chosenColor : Color) : KeywordsUiState()
}
