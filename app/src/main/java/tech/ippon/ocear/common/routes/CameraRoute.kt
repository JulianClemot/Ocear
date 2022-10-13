package tech.ippon.ocear.common.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import tech.ippon.ocear.common.ArKeyword
import tech.ippon.ocear.reader.CameraReaderScreen

class CameraRoute(private val goToArScreen: (List<ArKeyword>, chosenColor: Color) -> Unit) :
    NavigationRoute() {
    override val route = CAMERA_ROUTE

    override val arguments = listOf(
        navArgument(COLOR_CHOSEN) { type = NavType.LongType }
    )

    @Composable
    override fun Content(backStackEntry: NavBackStackEntry) {
        val colorChosen = requireNotNull(backStackEntry.arguments?.getLong(COLOR_CHOSEN)?.toULong())
        CameraReaderScreen(goToArScreen = goToArScreen, chosenColor = Color(colorChosen))
    }

    companion object {
        private const val COLOR_CHOSEN = "COLOR_CHOSEN"
        private const val CAMERA_COLOR_CHOSEN_PARAMETER = "{$COLOR_CHOSEN}"

        private const val CAMERA_ROUTE = "camera/$CAMERA_COLOR_CHOSEN_PARAMETER"
        fun asDirection(colorValue: ULong) = CAMERA_ROUTE.replace(
            CAMERA_COLOR_CHOSEN_PARAMETER,
            colorValue.toLong().toString()
        )
    }
}