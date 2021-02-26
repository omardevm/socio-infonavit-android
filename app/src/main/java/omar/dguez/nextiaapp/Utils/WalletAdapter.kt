package omar.dguez.nextiaapp.Utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import omar.dguez.nextiaapp.Models.Card
import omar.dguez.nextiaapp.R

class WalletAdapter(private var cardList: List<Card>?) :
    RecyclerView.Adapter<WalletAdapter.MyViewHolder>() {
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
        if (cardList !== null) {
            val movie: Card = cardList!![position]
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        return if (cardList !== null) {
            cardList!!.size
        } else {
            -1
        }
    }

    fun update(newList: List<Card>) {
        this.cardList = newList;
    }

    fun joinLists(newList: List<Card>) {
        this.cardList = this.cardList?.plus(newList)
    }

    /**
     * MyViewHolder
     */
    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.carditem, parent, false)) {
        private var cardDate: TextView? = null
        private var cardReach: TextView? = null
        private var cardDesc: TextView? = null
        private var cardImg: ImageView? = null
        private var cardImgBg: RelativeLayout? = null

        init {
            cardDate = itemView.findViewById(R.id.cardDate)
            cardReach = itemView.findViewById(R.id.cardReach)
            cardDesc = itemView.findViewById(R.id.cardDesc)
            cardImg = itemView.findViewById(R.id.cardImg)
            cardImgBg = itemView.findViewById(R.id.cardImgBg)
        }

        fun bind(card: Card) {
            cardDate?.text = card.cardDate
            cardReach?.text = card.cardReach
            cardDesc?.text = card.cardDesc
            Picasso.get().load(card.cardImg).into(cardImg)
        }
    }
}