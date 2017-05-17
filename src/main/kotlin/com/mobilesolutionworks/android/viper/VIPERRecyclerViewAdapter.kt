package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 16/5/17.
 */

abstract class VIPERRecyclerViewAdapter<VT : StrongTypedAdapter.ViewType, P : Presentable> : StrongTypedAdapter<P, VT, VIPERCellViewHolder<P>>()