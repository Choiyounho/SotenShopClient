package com.soten.sotenshopclient.ui.category

enum class CategoryState(
    val category: Int,
    val title: String,
) {

    NORMAL(Int.MIN_VALUE, "상태 없음"),
    T_SHIRT(0, "T-Shirt"),
    HOODIE(1, "Hoodie"),
    JEANS(2, "Jeans"),
    SHORTS(3, "Shorts"),
    SWEATER(4, "Sweater"),
    TRACKS(5, "Tracks")

}
