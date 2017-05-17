package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 15/5/17.
 */

interface Presentable

abstract class VIPERCellPresenter<E: Presentable> protected constructor() {

    abstract fun present(e: E?)
}