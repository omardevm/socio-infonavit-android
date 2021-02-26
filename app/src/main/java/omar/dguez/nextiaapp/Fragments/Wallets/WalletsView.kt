package omar.dguez.nextiaapp.Fragments.Wallets

import omar.dguez.nextiaapp.Models.Benevit

interface WalletsView {
    fun loadViews()
    fun loadBenevits(benevits: Benevit, list: ArrayList<String>?)
    fun error(msg: String)
}