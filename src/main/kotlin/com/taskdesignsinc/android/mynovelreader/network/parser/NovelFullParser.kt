package com.taskdesignsinc.android.mynovelreader.network.parser

import com.taskdesignsinc.android.mynovelreader.model.Novel

class NovelFullParser: BaseParser("Novel Full", "https://novelfull.com") {

    override val latestReleaseUrl: String = "${serverUrl}/index.php/latest-release-novel"

    override fun getPage(url: String, page: Int): String {
        return "$url?page=$page"
    }
    override fun getNovels(url: String): List<Novel> {
        val document = load(url)
        val novels = mutableListOf<Novel>()
        for (element in document.select("div[class=row]").select("div[class=col-xs-7]")) {
            val novelName = element.select("h3[class=truyen-title]").text().trim()
            val novelUrl = element.select("h3[class=truyen-title]").select("a").attr("abs:href")
            val novel = Novel(novelName, novelUrl)
            novels.add(novel)
        }
        return novels
    }

    override fun getNovel(url: String): Novel {
        TODO("Not yet implemented")
    }

    override fun getChapters(url: String) {
        TODO("Not yet implemented")
    }

}