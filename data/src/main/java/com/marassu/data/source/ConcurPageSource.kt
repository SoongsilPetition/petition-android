package com.marassu.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marassu.data.service.ConcurService
import com.marassu.entity.concur.AgreementStatus
import com.marassu.entity.concur.Concur
import java.lang.Exception

class ConcurPageSource(
    val service: ConcurService,
    val petitionId: Long): PagingSource<Int, Concur>() {
    override fun getRefreshKey(state: PagingState<Int, Concur>): Int? {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Concur> {
        val pageIndex = params.key ?: 1
        return try {
            val response = service.getConcurList(page = pageIndex, size = 10, petitionId = petitionId).body()
            val nextKey =
                if (response?.isEmpty() == true) {
                    null
                } else {
                    pageIndex + 1
                }

            LoadResult.Page(
                data = response!!,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = nextKey
            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }
}