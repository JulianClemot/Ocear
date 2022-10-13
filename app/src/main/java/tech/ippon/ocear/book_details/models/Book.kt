package tech.ippon.ocear.book_details.models

data class Book(
    val title: String,
    val shortDescription: String,
    val author: String,
    val coverResource: Int,
)