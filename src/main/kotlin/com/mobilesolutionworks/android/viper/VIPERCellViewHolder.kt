package com.mobilesolutionworks.android.viper

import android.support.v7.widget.RecyclerView

/**
 * Created by yunarta on 16/5/17.
 */

open class VIPERCellViewHolder<in E>(view: android.view.View, val presenter: VIPERCellPresenter<E>) : RecyclerView.ViewHolder(view) {

    companion object {

        public fun <V : com.mobilesolutionworks.android.viper.VIPERView, E> create(parent: android.view.ViewGroup, view: ((Int) -> android.view.View) -> V, create: (V) -> VIPERCellPresenter<E>): com.mobilesolutionworks.android.viper.VIPERCellViewHolder<E> {
            fun createView(parent: android.view.ViewGroup): (Int) -> android.view.View = { layout ->
                android.view.LayoutInflater.from(parent.context).inflate(layout, parent, false)
            }

            return view(createView(parent)).let { v: V ->
                com.mobilesolutionworks.android.viper.VIPERCellViewHolder(v.view, create(v))
            }
        }
    }
}