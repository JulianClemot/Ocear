package tech.ippon.ocear.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import tech.ippon.ocear.R

val interFont = FontFamily(
    Font(R.font.inter_regular),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(R.font.inter_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.inter_thin, weight = FontWeight.Thin),
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_extralight, weight = FontWeight.ExtraLight),
)

val appTitleStyle = TextStyle(
    fontFamily = interFont,
    fontWeight = FontWeight.W700,
    fontSize = 54.sp,
    color = textColor
)

val appDescriptionStyle = TextStyle(
    fontFamily = interFont,
    fontWeight = FontWeight.W300,
    fontSize = 16.sp,
    color = textColor
)

val titleStyle = TextStyle(
    fontFamily = interFont,
    fontWeight = FontWeight.W700,
    fontSize = 20.sp,
    color = textColor
)

val subTitleStyle = TextStyle(
    fontFamily = interFont,
    fontWeight = FontWeight.W500,
    fontSize = 17.sp,
    color = textColor
)

val contentStyle = TextStyle(
    fontFamily = interFont,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    color = textColor
)

val ctaStyle = TextStyle(
    fontFamily = interFont,
    fontWeight = FontWeight.W700,
    fontSize = 16.sp,
    color = Color.White
)