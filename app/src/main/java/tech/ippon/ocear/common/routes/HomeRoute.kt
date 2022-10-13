package tech.ippon.ocear.common.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import tech.ippon.ocear.book_details.models.Book
import tech.ippon.ocear.home.HomeScreen
import tech.ippon.ocear.ui.theme.BookColors

class HomeRoute(
    private val books: List<Book>,
    private val bookColors: List<BookColors>,
    private val goToScanBookPage: (ULong) -> Unit
) : NavigationRoute() {
    override val route = "home"

    @Composable
    override fun Content(backStackEntry: NavBackStackEntry) {
        HomeScreen(books = books, bookColors, goToScanBookPage = goToScanBookPage)
    }
}