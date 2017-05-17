package com.mobilesolutionworks.android.viper

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by yunarta on 16/5/17.
 */

abstract class StrongTypedAdapter<E, VT : StrongTypedAdapter.ViewType, VH : StrongTypedAdapter.StrongHolder<E>> : RecyclerView.Adapter<VH>() {

    interface ViewType {

        val ordinal: Int
    }

    private val viewTypes = mutableMapOf<Int, VT>()

    abstract fun getItem(position: Int): E

    final override fun getItemViewType(position: Int): Int {
        val viewType = getViewType(position)
        viewTypes[viewType.ordinal] = viewType

        return viewType.ordinal
    }

    final override fun onCreateViewHolder(parent: android.view.ViewGroup?, viewType: Int): VH {
        if (parent == null) throw IllegalArgumentException("creating view holder with null parent")
        val type = viewTypes[viewType] ?: throw IllegalArgumentException("view holder created with unregistered view type")

        return onCreateViewHolder(android.view.LayoutInflater.from(parent.context), parent, type)
    }

    abstract fun onCreateViewHolder(inflater: android.view.LayoutInflater, parent: android.view.ViewGroup, viewType: VT): VH

    final override fun onBindViewHolder(holder: VH, position: Int) {
        holder.present(getItem(position))
    }

    abstract fun getViewType(position: Int): VT

    abstract class StrongHolder<in E>(val view: View) : RecyclerView.ViewHolder(view) {

        abstract fun present(e: E)
    }
}