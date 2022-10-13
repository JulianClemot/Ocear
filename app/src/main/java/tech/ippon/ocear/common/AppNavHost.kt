package tech.ippon.ocear.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.ippon.ocear.common.routes.ArRoute
import tech.ippon.ocear.common.routes.CameraRoute
import tech.ippon.ocear.common.routes.HomeRoute
import tech.ippon.ocear.common.routes.NavigationRoute
import tech.ippon.ocear.home.HomeViewModel
import timber.log.Timber

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val homeViewModel = viewModel<HomeViewModel>()
    val homeRoute = HomeRoute(homeViewModel.books, homeViewModel.colors) {
        Timber.e("long : $it")
        navController.navigate(CameraRoute.asDirection(it))
    }
    val cameraRoute = CameraRoute { keywords, chosenColor ->
        navController.navigate(ArRoute.asDirection(keywords, chosenColor))
    }

    val arRoute = ArRoute()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = homeRoute.route
    ) {
        addDestination(homeRoute)
        addDestination(cameraRoute)
        addDestination(arRoute)
    }
}

private fun NavGraphBuilder.addDestination(
    navigationRoute: NavigationRoute,
) = composable(
    route = navigationRoute.route,
    arguments = navigationRoute.arguments
) { backStackEntry ->
    navigationRoute.Content(backStackEntry)
}