package tech.ippon.ocear.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.ippon.ocear.R
import tech.ippon.ocear.book_details.models.Book
import tech.ippon.ocear.ui.theme.*

@Composable
fun HomeScreen(books: List<Book>, colors: List<BookColors>, goToScanBookPage: (ULong) -> Unit) {
    LazyColumn() {
        item {
            Text(
                modifier = Modifier.padding(top = 24.dp, start = 33.dp, end = 33.dp),
                text = stringResource(id = R.string.app_name),
                style = appTitleStyle,
            )
        }
        item {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 33.dp, end = 33.dp),
                text = buildAnnotatedString {
                    append(stringResource(R.string.app_description_regular))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.app_description_bold))
                    }
                },
                style = appDescriptionStyle,
            )
        }
        itemsIndexed(books) { index, item ->
            BooksItem(item, colors[index], goToScanBookPage, books.lastIndex == index)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BookVisualizerTheme {
        HomeScreen(
            listOf(Book("title", "shortDescription", "author", R.drawable.under_the_sea)),
            listOf(earthToMoonColors)
        ) {}
    }
}