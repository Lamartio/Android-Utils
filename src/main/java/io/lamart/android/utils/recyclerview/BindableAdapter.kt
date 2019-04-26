/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_ID

abstract class BindableAdapter<T> : RecyclerView.Adapter<BindableViewHolder<T>>(), Bindable<List<T>>

fun <T> DiffList<T>.toBindableAdapter(
    create: (parent: ViewGroup, viewType: Int) -> BindableViewHolder<T>,
    bind: BindableViewHolder<T>.(item: T, position: Int) -> Unit = { item, _ -> bind(item) },
    getViewType: (item: T, position: Int) -> Int = { _, _ -> 0 },
    getId: ((item: T, position: Int) -> Any)? = null
): BindableAdapter<T> = object : BindableAdapter<T>() {

    private val list = this@toBindableAdapter

    init {
        setHasStableIds(getId != null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> =
        create(parent, viewType)

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) =
        bind(holder, list[position], position)

    override fun getItemViewType(position: Int): Int = getViewType(list[position], position)

    override fun getItemId(position: Int): Long =
        getId?.invoke(list[position], position)?.toAdapterId() ?: NO_ID

    override fun getItemCount(): Int = list.size

    override fun bind(content: List<T>) = list.set(content).dispatchUpdatesTo(this)

}