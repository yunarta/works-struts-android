package com.mobilesolutionworks.android.viper

import android.util.Log

/**
 * Created by yunarta on 16/5/17.
 */

abstract class VIPERRecyclerViewAdapter<VT : StrongTypedAdapter.ViewType, P : Presentable> : StrongTypedAdapter<P, VT, VIPERCellViewHolder<P>>() {

    override fun onViewRecycled(holder: VIPERCellViewHolder<P>?) {
        super.onViewRecycled(holder)
        holder?.onViewRecycled()
    }
}