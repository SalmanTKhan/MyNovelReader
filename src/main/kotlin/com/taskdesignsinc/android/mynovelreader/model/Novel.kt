package com.taskdesignsinc.android.mynovelreader.model

class Novel(val name: String, val url: String) {
    val chapters: MutableList<Chapter> = mutableListOf()
}
