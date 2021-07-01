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

package com.jack.reddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jack.reddit.adapters.RedditListAdapter
import com.jack.reddit.databinding.FragmentRedditListBinding
import com.jack.reddit.viewmodels.RedditListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RedditListFragment : Fragment() {
    val adapter = RedditListAdapter()
    private var getListJob: Job? = null
    private val viewModel: RedditListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRedditListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.redditList.adapter = adapter
        getList()

        return binding.root
    }

    private fun getList() {
        // Make sure we cancel the previous job before creating a new one
        getListJob?.cancel()
        getListJob = lifecycleScope.launch {
            viewModel.getList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}
