package com.taskdesignsinc.android.mynovelreader.network.parser

import com.taskdesignsinc.android.mynovelreader.model.Novel

class NovelFullParser: BaseParser("Novel Full", "https://novelfull.com/") {

    override val latestReleaseUrl: String = "${serverUrl}/latest-release-novel"

    override fun getPage(url: String, page: Int): String {
        return "$url?page=$page"
    }
    override fun getNovels(url: String): List<Novel> {
        TODO("Not yet implemented")
    }

}