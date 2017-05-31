package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 16/5/17.
 */

open class VIPERCellViewHolder<in T : Presentable>(view: android.view.View, internal val presenter: VIPERCellPresenter<T>) : StrongTypedAdapter.StrongHolder<T>(view) {

    override fun present(e: T) = presenter.present(e)

    companion object {
        public fun <V : VIPERViewImpl, E : Presentable> create(parent: android.view.ViewGroup, view: ((Int) -> android.view.View) -> V, create: (V) -> VIPERCellPresenter<E>): VIPERCellViewHolder<E> {
            fun createView(parent: android.view.ViewGroup): (Int) -> android.view.View {
                return { layout ->
                    android.view.LayoutInflater.from(parent.context).inflate(layout, parent, false)
                }
            }

            return view(createView(parent)).let { v: V ->
                val presenter: VIPERCellPresenter<E> = create(v)
                VIPERCellViewHolder(v.view, presenter)
            }
        }
    }

    open fun onViewRecycled() {
        presenter.recycling()
    }
}