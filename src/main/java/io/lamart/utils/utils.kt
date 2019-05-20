/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

@file:Suppress("UNCHECKED_CAST")

package io.lamart.utils

fun <T> Any?.cast() = this as T

inline fun <reified T> Any?.castIf(): T? = takeIf { it is T }.cast()

fun <T, R> T.fold(
    block: T.() -> R,
    catch: (e: Throwable) -> R,
    finally: () -> Unit = {}
): R =
    try {
        block()
    } catch (e: Throwable) {
        catch(e)
    } finally {
        finally()
    }

fun <R> fold(
    block: () -> R,
    catch: (e: Throwable) -> R,
    finally: () -> Unit = {}
): R =
    try {
        block()
    } catch (e: Throwable) {
        catch(e)
    } finally {
        finally()
    }

val Boolean.isTrue: Unit?
    get() = when (this) {
        true -> Unit
        false -> null
    }
val Boolean.isFalse: Unit?
    get() = when (this) {
        true -> null
        false -> Unit
    }