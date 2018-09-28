package serio.tim.android.com.tastedive.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import serio.tim.android.com.tastedive.R
import serio.tim.android.com.tastedive.databinding.RecyclerItemBinding
import serio.tim.android.com.tastedive.retrofit.Result

class TasteDiveAdapter : RecyclerView.Adapter<TasteDiveAdapter.ViewHolder>() {
    private var resultsList: ArrayList<Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var results = resultsList
        var result = results!![position]
        holder.recyclerItemBinding.result = result
    }

    fun setResultList(resultsList: ArrayList<Result>) {
        this.resultsList = resultsList
    }

    fun clear() {
        if(resultsList != null) {
            val size = resultsList?.size
            resultsList?.clear()
            notifyItemRangeRemoved(0, size!!)
        }
    }

    override fun getItemCount(): Int {
        return resultsList?.size!!
    }

    inner class ViewHolder(binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var recyclerItemBinding: RecyclerItemBinding = binding
    }

}