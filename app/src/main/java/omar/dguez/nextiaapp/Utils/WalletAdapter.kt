package omar.dguez.nextiaapp.Utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import omar.dguez.nextiaapp.Models.Card
import omar.dguez.nextiaapp.R

class WalletAdapter(private var cardList: ArrayList<Card>) :
    RecyclerView.Adapter<WalletAdapter.MyViewHolder>() {

    private val itemNumber = 5

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
        val movie: Card = cardList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun update(newList: ArrayList<Card>) {
        this.cardList = newList;
        notifyDataSetChanged()
    }

    fun joinLists(newList: ArrayList<Card>) {
        this.cardList = this.cardList.plus(newList) as ArrayList<Card>
        notifyDataSetChanged()
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
        private var cardButton: Button? = null
        private var cardImgBg: RelativeLayout? = null

        init {
            cardDate = itemView.findViewById(R.id.cardDate)
            cardReach = itemView.findViewById(R.id.cardReach)
            cardDesc = itemView.findViewById(R.id.cardDesc)
            cardImg = itemView.findViewById(R.id.cardImg)
            cardImgBg = itemView.findViewById(R.id.cardImgBg)
            cardButton = itemView.findViewById(R.id.buttonWant)
        }

        fun bind(card: Card) {
            if (card.unlocked) {
                cardDate?.text = card.cardDate
                cardReach?.text = card.cardReach
                cardDesc?.text = card.cardDesc
                cardImgBg?.setBackgroundColor(Color.parseColor(card.cardImgBg))
            } else {
                cardDate?.visibility = View.GONE
                cardReach?.visibility = View.GONE
                cardDesc?.visibility = View.GONE
                cardImgBg?.setBackgroundColor(Color.parseColor("#00FF0000"))
                cardButton?.visibility = View.VISIBLE
            }
            Picasso.get().load(card.cardImg).into(cardImg)
        }
    }
}