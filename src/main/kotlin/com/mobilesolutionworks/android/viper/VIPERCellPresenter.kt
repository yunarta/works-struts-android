package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 15/5/17.
 */

abstract class VIPERCellPresenter<in E> protected constructor() {

    abstract fun present(e: E?)
}