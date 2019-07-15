/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils

import android.content.Context
import android.content.ContextWrapper
import android.util.TypedValue

/**
 * Recursively traverses up the baseContext property and returns the first Context that is a type of `T`.
 */

inline fun <reified T : Any> Context.find(inApplicationContext: Boolean = true): T? =
    find(T::class.java, inApplicationContext)

/**
 * Recursively traverses up the baseContext property and returns the first Context that is the same Class.
 */

fun <T : Any> Context.find(type: Class<T>, inApplicationContext: Boolean = true): T? =
    when {
        type.isInstance(this) -> {
            @Suppress("UNCHECKED_CAST")
            this as T
        }
        this is ContextWrapper -> baseContext.find(type, inApplicationContext)
        inApplicationContext -> applicationContext.find(type, false)
        else -> null
    }


infix fun Context.dip(value: Int): Int = value.toFloat().let(::dip).toInt()

infix fun Context.dip(value: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)