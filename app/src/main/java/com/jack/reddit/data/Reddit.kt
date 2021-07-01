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
    @field:SerializedName("subreddit_id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val redditId: String,

    @field:SerializedName("approved_at_utc")
    val approved_at_utc: String = "",
    @field:SerializedName("subreddit")
    val subreddit: String = "",
    @field:SerializedName("selftext")
    val selftext: String = "",
    @field:SerializedName("title")
    val title: String = "",
    @field:SerializedName("name")
    val name: String = "",
    @field:SerializedName("upvote_ratio")
    val upvote_ratio: Double = 0.0,
    @field:SerializedName("ups")
    val ups: Int = 0,
    @field:SerializedName("total_awards_received")
    val total_awards_received: Int = 0,
    @field:SerializedName("selftext_html")
    val selftext_html: String = "",
    @field:SerializedName("permalink")
    val permalink: String = ""
) {
    override fun toString() = name
}
