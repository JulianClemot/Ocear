package tech.ippon.ocear.reader.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YakeKeywordsResult(
    @SerialName("keywords")
    val keywords: List<YakeKeyword>,
    @SerialName("language")
    val language: String,
)

@Serializable
data class YakeKeyword(
    @SerialName("ngram") val label: String,
    @SerialName("score") val score: Double,
)
