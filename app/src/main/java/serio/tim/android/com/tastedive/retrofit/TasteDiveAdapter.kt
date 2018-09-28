package serio.tim.android.com.tastedive.retrofit

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item.view.*
import serio.tim.android.com.tastedive.R.layout.recycler_item

class TasteDiveAdapter : RecyclerView.Adapter<TasteDiveAdapter.ViewHolder>() {
    private var resultsList: ArrayList<Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results = resultsList
        holder.name.text = results?.get(position)?.Name
        holder.type.text = results?.get(position)?.Type
    }

    fun clear() {
        if(resultsList != null) {
            val size = resultsList?.size
            resultsList?.clear()
            notifyItemRangeRemoved(0, size!!)
        }
    }

    fun setResultList(resultsList: ArrayList<Result>) {
        this.resultsList = resultsList
    }

    override fun getItemCount(): Int {
        return resultsList?.size!!
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.text_result_name
        var type: TextView = view.text_result_type
    }

}