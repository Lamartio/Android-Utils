/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.kontext

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

fun Context.kontext(): Lazy<Kontext> = lazy { kontext }
fun Fragment.kontext(): Lazy<Kontext> = lazy { kontext }
fun View.kontext(): Lazy<Kontext> = lazy { kontext }

val Context.kontext: Kontext
    get() = Kontext(this)

val Fragment.kontext: Kontext
    get() = requireContext().kontext

val View.kontext: Kontext
    get() = context.kontext


interface Kontext : KontextSource, KontextCompat, Dips {

    companion object {

        operator fun invoke(context: Context): Kontext = object : Kontext {

            override val context: Context = context


        }

    }

}