package com.taskdesignsinc.android.mynovelreader.model

class Novel(val title: String, val url: String) {
    var coverImageUrl: String = ""
    val chapters: MutableList<Chapter> = mutableListOf()
}
