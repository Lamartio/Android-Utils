/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.kontext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

interface KontextCompat : KontextSource {

    fun startActivity(intent: Intent, options: Bundle?) =
        ContextCompat.startActivity(context, intent, options)

    fun getColor(@ColorRes id: Int) =
        ContextCompat.getColor(context, id)

    fun getDrawable(@DrawableRes id: Int) =
        ContextCompat.getDrawable(context, id)

    fun getColorStateList(@ColorRes id: Int) =
        ContextCompat.getColorStateList(context, id)

    fun <T> getSystemService(type: Class<T>) =
        ContextCompat.getSystemService(context, type)

    fun getSystemServiceName(type: Class<*>) =
        ContextCompat.getSystemServiceName(context, type)

    companion object {

        operator fun invoke(context: Context) = object : KontextCompat {

            override val context: Context = context

        }

    }

}

inline fun <reified T> KontextCompat.getSystemService() = getSystemService(T::class.java)
inline fun <reified T> KontextCompat.getSystemServiceName() = getSystemServiceName(T::class.java)
