package tech.ippon.ocear.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import tech.ippon.ocear.R
import tech.ippon.ocear.book_details.models.Book
import tech.ippon.ocear.ui.theme.*

@Composable
fun BooksItem(book: Book, bookColors: BookColors, onBookItemClick: (ULong) -> Unit, isLastItem: Boolean) {
    val itemBottomPadding = if(isLastItem) {
        16.dp
    } else {
        0.dp
    }
    ConstraintLayout(modifier = Modifier.padding(top = 16.dp, start = 33.dp, end = 42.dp, bottom = itemBottomPadding)) {
        val (illustration, background, title, author, description, button) = createRefs()
        val backgroundTopGuideline = createGuidelineFromTop(44.dp)

        Box(modifier = Modifier
            .constrainAs(background) {
                top.linkTo(backgroundTopGuideline)
                linkTo(parent.start, parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
            }
            .clip(RoundedCornerShape(20.dp))
            .background(bookColors.backgroundColor)
        )
        Image(
            modifier = Modifier
                .constrainAs(illustration) {
                    top.linkTo(parent.top)
                    linkTo(parent.start, parent.end, bias = 0F, startMargin = 12.dp)
                    width = Dimension.preferredWrapContent
                    height = Dimension.preferredWrapContent
                },
            painter = painterResource(id = book.coverResource),
            contentDescription = stringResource(R.string.illustration_content_description),
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(illustration.bottom, margin = 4.dp)
                    linkTo(parent.start, parent.end, bias = 0F, startMargin = 22.dp, endMargin = 22.dp)
                    width = Dimension.preferredWrapContent
                },
            text = book.title,
            style = titleStyle,
        )
        Text(
            modifier = Modifier
                .constrainAs(author) {
                    top.linkTo(title.bottom, margin = 2.dp)
                    linkTo(title.start, title.end, bias = 0F)
                    width = Dimension.preferredWrapContent
                },
            text = book.author,
            style = subTitleStyle,
        )
        Text(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(author.bottom, margin = 6.dp)
                    linkTo(title.start, title.end, bias = 0F)
                    width = Dimension.preferredWrapContent
                },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = book.shortDescription,
            style = contentStyle,
        )
        Button(
            onClick = { onBookItemClick(bookColors.buttonColor.value) },
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(description.bottom, margin = 16.dp)
                    linkTo(
                        title.start,
                        parent.end,
                        bias = 0F,
                        startMargin = 0.dp,
                        endMargin = 22.dp
                    )
                    width = Dimension.preferredWrapContent
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
            colors = ButtonDefaults.buttonColors(bookColors.buttonColor)
        ) {
            Text(
                text = stringResource(R.string.start_reading),
                style = ctaStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BooksItemPreview() {
    BookVisualizerTheme {
        BooksItem(
            Book("title", "shortDescription", "author", R.drawable.under_the_sea),
            leaguesUnderTheSeaColors,
            {},
            false
        )
    }
}