package com.taskdesignsinc.android.mynovelreader.ui.novellist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskdesignsinc.android.mynovelreader.model.Novel
import com.taskdesignsinc.android.mynovelreader.network.parser.NovelFullParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NovelListViewModel: ViewModel() {
    var currentPage: Int = 1
    fun getNovels(): MutableList<Novel> {
        var novels = mutableListOf<Novel>()
        // Create a new coroutine to move the execution off the UI thread
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val currentParser = NovelFullParser()
                novels = currentParser.getNovels(currentParser.getPage(currentParser.latestReleaseUrl, currentPage))
                    .toMutableList()
            }
        }
        return novels
    }
}