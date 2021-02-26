package omar.dguez.nextiaapp.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import omar.dguez.nextiaapp.Models.Card
import omar.dguez.nextiaapp.R


class MainRecyclerAdapter(
    private var list: ArrayList<String>,
    private var sectionMap: HashMap<String, ArrayList<Card>>,
) :
    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.section_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sectionName: String = list[position]
        val items: ArrayList<Card> = sectionMap[sectionName]!!
        holder.sectionNameTextView.text = sectionName
        val childRecyclerAdapter = WalletAdapter(items)
        holder.childRecyclerView.adapter = childRecyclerAdapter
    }

    override fun getItemCount(): Int {
        return sectionMap.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionNameTextView: TextView = itemView.findViewById(R.id.sectionNameTextView)
        var childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)

    }

}