/*
 * Copyright (c) 2019 Danny Lamarti
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package io.lamart.android.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle

fun activityLifecycleCallbacks(
    onCreated: (activity: Activity, savedInstanceState: Bundle?) -> Unit = { _, _ -> },
    onStarted: (activity: Activity) -> Unit = { },
    onResumed: (activity: Activity) -> Unit = { },
    onPaused: (activity: Activity) -> Unit = { },
    onStopped: (activity: Activity) -> Unit = { },
    onDestroyed: (activity: Activity) -> Unit = { },
    onSaveInstanceState: (activity: Activity, outState: Bundle) -> Unit = { _, _ -> }
) = object : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) =
        onCreated.invoke(activity, savedInstanceState)

    override fun onActivityStarted(activity: Activity) = onStarted.invoke(activity)

    override fun onActivityResumed(activity: Activity) = onResumed.invoke(activity)

    override fun onActivityPaused(activity: Activity) = onPaused.invoke(activity)

    override fun onActivityStopped(activity: Activity) = onStopped.invoke(activity)

    override fun onActivityDestroyed(activity: Activity) = onDestroyed.invoke(activity)

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) =
        onSaveInstanceState.invoke(activity, outState)

}