/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.recyclerview

import androidx.recyclerview.widget.DiffUtil


class DiffList<T>(
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem === newItem },
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    private val detectMoves: Boolean = true,
    private val newItems: MutableList<T> = mutableListOf()
) : DiffUtil.Callback(), List<T> by newItems {

    private val oldItems: MutableList<T> = mutableListOf()

    fun set(items: List<T>): DiffUtil.DiffResult {
        oldItems.apply { clear(); addAll(newItems) }
        newItems.apply { clear(); addAll(items) }

        return DiffUtil.calculateDiff(this, detectMoves)
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

}


