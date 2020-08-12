import com.taskdesignsinc.android.mynovelreader.network.parser.NovelFullParser

object ParserRunner {

    @JvmStatic
    fun main(args: Array<String>) {
        val parser = NovelFullParser()
        val novels = parser.getNovels(parser.getPage(parser.latestReleaseUrl, 1))
        println("$novels")
        val novel = parser.getNovel(novels[0].url)
        println(novel)
    }
}