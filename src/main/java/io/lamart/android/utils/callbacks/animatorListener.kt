/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils.callbacks

import android.animation.Animator

fun animatorListener(
    onAnimationStart: (animation: Animator) -> Unit = {},
    onAnimationEnd: (animation: Animator) -> Unit = {},
    onAnimationCancel: (animation: Animator) -> Unit = {},
    onAnimationRepeat: (animation: Animator) -> Unit = {}
) = object : Animator.AnimatorListener {

    override fun onAnimationStart(animation: Animator) = onAnimationStart.invoke(animation)

    override fun onAnimationEnd(animation: Animator) = onAnimationEnd.invoke(animation)

    override fun onAnimationCancel(animation: Animator) = onAnimationCancel.invoke(animation)

    override fun onAnimationRepeat(animation: Animator) = onAnimationRepeat.invoke(animation)

}