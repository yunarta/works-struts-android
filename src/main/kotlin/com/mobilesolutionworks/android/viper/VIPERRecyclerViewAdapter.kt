package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 16/5/17.
 */

abstract class VIPERRecyclerViewAdapter<VT : StrongTypedAdapter.ViewType, ET> : StrongTypedAdapter<VT, VIPERCellViewHolder<ET>>() {

    final override fun onBindViewHolder(holder: VIPERCellViewHolder<ET>, position: Int) {
        onBindViewHolder(holder.presenter, position)
    }

    open fun onBindViewHolder(presenter: VIPERCellPresenter<ET>, position: Int) {
        presenter.present(getItem(position))
    }

    abstract fun getItem(position: Int): ET?
}