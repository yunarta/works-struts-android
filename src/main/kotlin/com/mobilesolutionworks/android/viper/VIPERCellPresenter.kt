package com.mobilesolutionworks.android.viper

abstract class VIPERCellPresenter<in E> {

    abstract fun present(e: E?)
}