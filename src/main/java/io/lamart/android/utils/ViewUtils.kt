/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.widget.TextViewCompat.setTextAppearance
import androidx.fragment.app.Fragment
import io.lamart.utils.Dispose
import io.lamart.utils.dispose

@Suppress("UNCHECKED_CAST")
infix fun <T : View> ViewGroup.inflate(@LayoutRes resource: Int): T =
    LayoutInflater
        .from(context)
        .inflate(resource, this, false)
        .let { it as T }

@Suppress("UNCHECKED_CAST")
fun <T : View> ViewGroup.addView(@LayoutRes resource: Int): View = inflate<T>(resource).also(::addView)

operator fun <T : View> View.get(@IdRes id: Int): T = findViewById(id)

fun <T : View> Activity.bindView(@IdRes id: Int) = lazy { findViewById<T>(id) }

fun <T : View> Fragment.bindView(@IdRes id: Int) = lazy { view?.findViewById<T>(id) }

fun <T : View> View.bindView(@IdRes id: Int) = lazy { findViewById<T>(id) }

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) =
        when (value) {
            true -> visibility = View.VISIBLE
            false -> visibility = View.GONE
        }

infix fun ViewGroup.add(view: View): Dispose {
    addView(view)
    return dispose { removeView(view) }
}

fun View.clicks(block: () -> Unit): Dispose {
    setOnClickListener { block() }
    return dispose { setOnClickListener(null) }
}

var TextView.textAppearance: Int
    get() = throw UnsupportedOperationException()
    set(value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextAppearance(value)
        } else {
            setTextAppearance(context, value)
        }
    }