package tech.ippon.ocear.common.routes

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import io.github.sceneview.ar.ArSceneView
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import tech.ippon.ocear.ar.ArScreen
import tech.ippon.ocear.ar.ArViewModel
import tech.ippon.ocear.common.ArKeyword
import tech.ippon.ocear.common.getParcelableObject

class ArRoute : NavigationRoute() {
    override val route = AR_ROUTE

    override val arguments = listOf(
        navArgument(AR_KEYWORDS) { type = ArScreenData.navType }
    )

    @Composable
    override fun Content(backStackEntry: NavBackStackEntry) {
        val arguments = requireNotNull(backStackEntry.arguments)
        val arScreenData = requireNotNull(
            arguments.getParcelableObject(
                AR_KEYWORDS,
                ArScreenData::class.java
            )
        )
        val arKeywords = arScreenData.keywords
        val chosenColor = Color(arScreenData.chosenColor)
        val arViewModel: ArViewModel = hiltViewModel()
        arViewModel.chosenColor = chosenColor
        val sceneViewUiState by arViewModel.arScreenUiState.collectAsState()
        val keywordsUiState by arViewModel.keywordsUiState.collectAsState()

        val onArSceneView: (ArSceneView) -> Unit = {
            arViewModel.initSceneViewWithModels(it, arKeywords)
        }

        ArScreen(
            sceneViewUiState,
            keywordsUiState,
            onArSceneView,
            arViewModel.onModelSelected,
            arViewModel.onModelAnchored,
            arViewModel.onModelSelectClick,
            arViewModel.onModelAnchorClick
        )
    }

    companion object {
        private const val AR_KEYWORDS = "AR_KEYWORDS"
        private const val AR_KEYWORDS_TYPE_PARAMETER = "{$AR_KEYWORDS}"

        private const val AR_ROUTE = "ar/$AR_KEYWORDS_TYPE_PARAMETER"
        fun asDirection(arKeywords: List<ArKeyword>, chosenColor: Color) = AR_ROUTE.replace(
            AR_KEYWORDS_TYPE_PARAMETER,
            Uri.encode(ArScreenData(arKeywords, chosenColor.value).toJson())
        )
    }

    @Parcelize
    @Serializable
    data class ArScreenData(val keywords: List<ArKeyword>, val chosenColor: ULong) : Parcelable {

        fun toJson() = Json.encodeToString(this)

        companion object {
            val navType = object : NavType<ArScreenData>(isNullableAllowed = false) {
                override fun get(bundle: Bundle, key: String) =
                    bundle.getParcelableObject(key, ArScreenData::class.java)

                override fun parseValue(value: String): ArScreenData = Json.decodeFromString(value)

                override fun put(bundle: Bundle, key: String, value: ArScreenData) {
                    bundle.putParcelable(key, value)
                }
            }
        }
    }
}