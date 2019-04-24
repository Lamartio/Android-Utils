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

inline fun <reified T : Any> Context.find(tryApplicationContext: Boolean = true): T? =
    find(T::class.java, tryApplicationContext)

@Suppress("UNCHECKED_CAST")
fun <T : Any> Context.find(type: Class<T>, inApplicationContext: Boolean = true): T? =
    when {
        type.isInstance(this) -> this as T
        this is ContextWrapper -> baseContext.find(type, inApplicationContext)
        inApplicationContext -> applicationContext.find(type, false)
        else -> null
    }