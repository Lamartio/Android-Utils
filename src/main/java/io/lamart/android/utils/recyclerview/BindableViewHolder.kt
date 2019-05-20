/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindableViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), Bindable<T> {

    companion object {

        operator fun <T> invoke(view: View, bind: (view: View, item: T) -> Unit) =
            object : BindableViewHolder<T>(view) {

                override fun bind(content: T) = bind.invoke(view, content)

            }
    }

}

fun <T> View.toBindableViewHolder(block: (view: View) -> (item: T) -> Unit): BindableViewHolder<T> =
    object : BindableViewHolder<T>(this@toBindableViewHolder) {

        val bind = block(this@toBindableViewHolder)

        override fun bind(content: T) = bind.invoke(content)

    }


private fun test(view: View) {

    view.toBindableViewHolder<String> {

        { item ->

        }
    }
}
