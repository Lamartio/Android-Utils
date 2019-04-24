/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.utils

typealias Dispose = () -> Unit

val dispose: Dispose = {}
fun dispose(value: Dispose): Dispose = value
fun dispose(vararg values: Dispose = arrayOf()): Dispose =
    values.reduce { previous, next -> { previous(); next() } }

operator fun Dispose.plus(dispose: Dispose): Dispose = dispose(this, dispose)

interface Disposable {

    fun dispose()

}

fun Disposable.toDispose(): Dispose = ::dispose

interface Disposables : Disposable, MutableCollection<Dispose> {

    operator fun Dispose.unaryPlus(): Boolean

    operator fun Dispose.unaryMinus(): Boolean

    companion object {

        operator fun invoke(collection: MutableCollection<Dispose> = mutableListOf()): Disposables =
            object : Disposables, MutableCollection<Dispose> by collection {

                override fun dispose() {
                    removeAll { dispose -> dispose.invoke(); true }
                }

                override fun Dispose.unaryPlus() = collection.add(this)

                override fun Dispose.unaryMinus() = collection.remove(this)

            }

    }

}
