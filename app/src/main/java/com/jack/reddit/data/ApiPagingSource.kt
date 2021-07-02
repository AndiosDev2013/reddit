/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jack.reddit.data

import androidx.paging.PagingSource
import com.jack.reddit.api.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

private const val STARTING_PAGE_INDEX = 1

class ApiPagingSource(
    private val service: ApiService,
    private val redditRepository: RedditRepository
) : PagingSource<Int, Reddit>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Reddit> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getHot()
            val data = ArrayList<Reddit>()
            for (i in 0 until response.data.children.size) {
                val item = response.data.children[i].data
                data.add(item)
            }

            GlobalScope.launch {
                redditRepository.insertReddits(data)
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == 0) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
