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

package com.jack.reddit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jack.reddit.RedditListFragment
import com.jack.reddit.data.Reddit
import com.jack.reddit.databinding.ListItemRedditBinding

/**
 * Adapter for the [RecyclerView] in [RedditListFragment].
 */
class RedditAdapter : ListAdapter<Reddit, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(
            ListItemRedditBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reddit = getItem(position)
        (holder as PlantViewHolder).bind(reddit)
    }

    class PlantViewHolder(
        private val binding: ListItemRedditBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.reddit?.let { reddit ->
                    navigateToReddit(reddit, it)
                }
            }
        }

        private fun navigateToReddit(reddit: Reddit, view: View
        ) {
            //val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(reddit.subreddit_id)
            //view.findNavController().navigate(direction)
        }

        fun bind(item: Reddit) {
            binding.apply {
                reddit = item
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Reddit>() {

    override fun areItemsTheSame(oldItem: Reddit, newItem: Reddit): Boolean {
        return oldItem.redditId == newItem.redditId
    }

    override fun areContentsTheSame(oldItem: Reddit, newItem: Reddit): Boolean {
        return oldItem == newItem
    }
}
