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

package com.jack.reddit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jack.reddit.RedditListFragment
import com.jack.reddit.RedditListFragmentDirections
import com.jack.reddit.adapters.RedditListAdapter.RedditListViewHolder
import com.jack.reddit.databinding.ListItemRedditBinding
import com.jack.reddit.data.Reddit

/**
 * Adapter for the [RecyclerView] in [RedditListFragment].
 */

class RedditListAdapter : PagingDataAdapter<Reddit, RedditListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditListViewHolder {
        return RedditListViewHolder(
            ListItemRedditBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RedditListViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class RedditListViewHolder(
        private val binding: ListItemRedditBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.reddit?.let { reddit ->
                    val action = RedditListFragmentDirections.actionRedditListToDetail(reddit.redditId)
                    view.findNavController().navigate(action)
                }
            }
        }

        fun bind(item: Reddit) {
            binding.apply {
                reddit = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Reddit>() {
    override fun areItemsTheSame(oldItem: Reddit, newItem: Reddit): Boolean {
        return oldItem.redditId == newItem.redditId
    }

    override fun areContentsTheSame(oldItem: Reddit, newItem: Reddit): Boolean {
        return oldItem == newItem
    }
}
