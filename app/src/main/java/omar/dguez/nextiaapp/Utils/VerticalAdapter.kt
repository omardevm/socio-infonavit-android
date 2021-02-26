package omar.dguez.nextiaapp.Utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import omar.dguez.nextiaapp.R

class VerticalAdapter(private var verticalList: List<String>?) :
    RecyclerView.Adapter<VerticalAdapter.MyViewHolder>() {
    /**
     * Inflates the viewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    /**
     * Triggers the viewHolder bind for render
     * and event listener purposes, one movie at a time
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (verticalList !== null) {
            val str: String = verticalList!![position]
            holder.bind(str)
        }
    }

    override fun getItemCount(): Int {
        return if (verticalList !== null) {
            verticalList!!.size
        } else {
            -1
        }
    }

    fun update(newList: List<String>) {
        this.verticalList = newList;
    }

    fun joinLists(newList: List<String>) {
        this.verticalList = this.verticalList?.plus(newList)
    }

    /**
     * MyViewHolder
     */
    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.carditem, parent, false)) {
        private var cardDate: TextView? = null

        init {
            cardDate = itemView.findViewById(R.id.cardDate)
        }

        fun bind(wallet_name: String) {
            //cardDate?.text = card.cardDate
        }
    }
}