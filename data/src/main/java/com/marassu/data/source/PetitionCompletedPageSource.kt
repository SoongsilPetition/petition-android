package com.marassu.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marassu.data.service.PetitionService
import com.marassu.entity.petition.Petition
import com.marassu.entity.petition.Sort

class PetitionCompletedPageSource(
    val service: PetitionService,
    val sort: Sort,
    val category: String?
) :
    PagingSource<Int, Petition>() {
    override fun getRefreshKey(state: PagingState<Int, Petition>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Petition> {
        val pageIndex = params.key ?: 1
        return try {
            val response =
                service.getCompletedPetitionList(page = pageIndex, sort = sort, category = category).body()
            val petitions = response
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
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}