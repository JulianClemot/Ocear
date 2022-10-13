package tech.ippon.ocear.ui.theme

import androidx.compose.ui.graphics.Color

val textColor = Color(0xFF404040)

val earthToMoonColors = BookColors(
    backgroundColor = Color(0xFFFFC4B7),
    buttonColor = Color(0xFFEF3911),
)
val leaguesUnderTheSeaColors = BookColors(
    backgroundColor = Color(0xFFE2F0EF),
    buttonColor = Color(0xFF108E85),
)

val journeyToTheCenterOfEarthColors = BookColors(
    backgroundColor = Color(0xFFF7DDA3),
    buttonColor = Color(0xFFFFB000),
)

data class BookColors(val backgroundColor: Color, val buttonColor: Color)