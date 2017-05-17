package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 16/5/17.
 */

abstract class VIPERRecyclerViewAdapter<VT : StrongTypedAdapter.ViewType, ET : Presentable> : StrongTypedAdapter<VT, VIPERCellViewHolder<out ET>>() {

    final override fun onBindViewHolder(holder: VIPERCellViewHolder<out ET>, position: Int) {
        onBindViewHolder(holder.presenter, position)
    }

    public open fun onBindViewHolder(presenter: VIPERCellPresenter<out ET>, position: Int) {
        val e = getItem(position)
        presenter.present(e)
    }

    abstract fun getItem(position: Int): ET?
}