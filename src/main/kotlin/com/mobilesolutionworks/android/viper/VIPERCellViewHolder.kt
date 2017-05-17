package com.mobilesolutionworks.android.viper

import android.support.v7.widget.RecyclerView

/**
 * Created by yunarta on 16/5/17.
 */

open class VIPERCellViewHolder<T : Presentable>(view: android.view.View, val presenter: VIPERCellPresenter<T>) : RecyclerView.ViewHolder(view) {

    companion object {

        public fun <V : VIPERView, E : Presentable> create(parent: android.view.ViewGroup, view: ((Int) -> android.view.View) -> V, create: (V) -> VIPERCellPresenter<E>): VIPERCellViewHolder<E> {
            fun createView(parent: android.view.ViewGroup): (Int) -> android.view.View = { layout ->
                android.view.LayoutInflater.from(parent.context).inflate(layout, parent, false)
            }

            return view(createView(parent)).let { v: V ->
                val presenter: VIPERCellPresenter<E> = create(v)
                VIPERCellViewHolder(v.view, presenter)
            }
        }
    }
}