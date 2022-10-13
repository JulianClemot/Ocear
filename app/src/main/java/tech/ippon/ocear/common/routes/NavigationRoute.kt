package tech.ippon.ocear.common.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

sealed class NavigationRoute {
    abstract val route: String

    @Composable
    abstract fun Content(backStackEntry: NavBackStackEntry)

    open val arguments: List<NamedNavArgument> = emptyList()
}