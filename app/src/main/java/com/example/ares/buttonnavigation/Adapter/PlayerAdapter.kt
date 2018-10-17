package com.example.ares.buttonnavigation.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ares.buttonnavigation.Model.Player
import com.example.ares.buttonnavigation.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerAdapter (val ctx:Context,val teams:List<Player>,val listener:(Player)->Unit)
    :RecyclerView.Adapter<PlayerHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):PlayerHolder {
        return  PlayerHolder(TeamUi<ViewGroup>().createView(AnkoContext.create(p0.context,p0)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: PlayerHolder, p1: Int) {
    p0.bindItem(teams[p1], listener, ctx)

    }

}

class PlayerHolder(view: View):RecyclerView.ViewHolder(view) {

    private val badge : ImageView = view.find(R.id.imgTeam)
    private val name :TextView = view.find(R.id.tv_namaTeam)
    private val posisi:TextView = view.find(R.id.tvPosisi)

    fun bindItem(player: Player, listener: (Player) -> Unit, ctx: Context){
        name.text = player.namaPemain
        posisi.text = player.Posisi

        if (player.pasPhoto != null){
            Picasso.get().load(player.pasPhoto).into(badge)
        }else badge.image = ContextCompat.getDrawable(ctx,R.drawable.ic_broken_image_black_24dp)

        itemView.setOnClickListener {
            listener(player)
        }
    }


}

class TeamUi<T>: AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>): View {
        return with(ui) {
            linearLayout {
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.imgTeam
                }.lparams(width = dip(80), height = dip(60))


                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(5)

                    textView {
                        id = R.id.tv_namaTeam
                        textSize = 16f
                        textColor = Color.BLUE
                        setTypeface(typeface,Typeface.BOLD)
                    }.lparams(matchParent, wrapContent) {

                    }
                    textView{
                        id = R.id.tvPosisi
                        textSize = 14f
                    }.lparams(matchParent, wrapContent){

                    }

                }.lparams(matchParent, wrapContent) {
                    margin = dip(10)
                }

            }
        }
    }
}
