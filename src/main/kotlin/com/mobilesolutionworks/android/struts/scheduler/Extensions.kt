package com.mobilesolutionworks.android.struts.scheduler

import android.app.job.JobParameters

/**
 * Created by yunarta on 19/5/17.
 */

interface PersistableBundleIndexer<out T> {

    operator fun get(name: String): T?
}

val JobParameters.strings: PersistableBundleIndexer<String>
    get() = object : PersistableBundleIndexer<String> {
        override operator fun get(name: String): String? = extras.getString(name)
    }

val JobParameters.ints: PersistableBundleIndexer<Int>
    get() = object : PersistableBundleIndexer<Int> {
        override operator fun get(name: String): Int? = extras.getInt(name)
    }
