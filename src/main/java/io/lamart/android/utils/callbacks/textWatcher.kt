/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.callbacks

import android.text.Editable
import android.text.TextWatcher

fun textWatcher(
    afterTextChanged: (s: Editable?) -> Unit = {},
    beforeTextChanged: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    onTextChanged: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> }
) = object : TextWatcher {

    override fun afterTextChanged(s: Editable?) = afterTextChanged.invoke(s)

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
        beforeTextChanged.invoke(s, start, count, after)

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
        onTextChanged.invoke(s, start, before, count)

}

