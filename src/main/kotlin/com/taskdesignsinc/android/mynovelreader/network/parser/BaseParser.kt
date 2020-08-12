package com.taskdesignsinc.android.mynovelreader.network.parser

import com.taskdesignsinc.android.mynovelreader.model.Novel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

abstract class BaseParser(var name: String = "Unknown", val serverUrl: String = "Constco.com") {
    private val client = OkHttpClient()

    open val latestReleaseUrl = ""

    fun load(url: String): Document {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return Jsoup.parse(response.body?.string())
    }

    open fun getPage(url: String, page: Int): String {
        return url
    }
    abstract fun getNovels(url: String): List<Novel>
    abstract fun getNovel(url: String): Novel
    abstract fun getChapters(url: String)
}