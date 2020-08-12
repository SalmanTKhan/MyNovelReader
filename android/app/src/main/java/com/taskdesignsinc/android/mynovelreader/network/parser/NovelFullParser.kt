package com.taskdesignsinc.android.mynovelreader.network.parser

import com.taskdesignsinc.android.mynovelreader.model.Chapter
import com.taskdesignsinc.android.mynovelreader.model.Novel

class NovelFullParser: BaseParser("Novel Full", "https://novelfull.com") {

    override val latestReleaseUrl: String = "${serverUrl}/index.php/latest-release-novel"

    override fun getPage(url: String, page: Int): String {
        return "$url?page=$page"
    }
    override fun getNovels(url: String): List<Novel> {
        val document = loadDocument(url)
        val novels = mutableListOf<Novel>()
        for (element in document.select("div[class=list list-truyen col-xs-12]").select("div[class=row]")) {
            val novelName = element.select("div[class=col-xs-7]").select("h3[class=truyen-title]").text().trim()
            val novelUrl = element.select("div[class=col-xs-7]").select("a").first().attr("abs:href")
            val novel = Novel(novelName, novelUrl)
            novel.coverImageUrl = element.select("div[class=col-xs-3]").select("img").attr("abs:src")
            novels.add(novel)
        }
        return novels
    }

    override fun getNovel(url: String): Novel {
        val document = loadDocument(url)
        val title = document.select("h3[class=title]").text().trim()
        val novel = Novel(title, url)
        val lastPage = document.select("ul[class=pagination pagination-sm]").select("li[class=last]").select("a").attr("data-page").toInt() + 1
        for (i in 1..lastPage)
            novel.chapters.addAll(getChapters(novel.url+"?page=${i}&per-page=50"))
        return novel
    }

    override fun getChapters(url: String): List<Chapter> {
        val document = loadDocument(url)
        val chapters = mutableListOf<Chapter>()
        for (element in document.select("ul[class=list-chapter]").select("li")) {
            val chapterTitle = element.select("a").select("span").text().trim()
            val chapterUrl = element.select("a").attr("abs:href")
            val chapter = Chapter(chapterTitle, chapterUrl)
            chapters.add(chapter)
        }
        return chapters
    }

    override fun getChapterText(url: String): String {
        val document = loadDocument(url)
        val chapterText = document.select("div[id=chapter-content]").html()
        chapterText.replace("If you find any errors ( broken links, non-standard content, etc.. ), Please let us know &lt; report chapter &gt; so we can fix it as soon as possible.", "")
        return chapterText
    }
}