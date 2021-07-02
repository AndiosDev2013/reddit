/*
 * Copyright 2018 Google LLC
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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "reddits")
data class Reddit(
    @field:SerializedName("name")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val redditId: String,

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("author_fullname")
    val author_fullname: String = "",

    @field:SerializedName("selftext")
    val selftext: String = "",
    @field:SerializedName("selftext_html")
    val selftext_html: String?,

    @field:SerializedName("created_utc")
    val created_utc: Long,
) {
    override fun toString() = selftext
}
