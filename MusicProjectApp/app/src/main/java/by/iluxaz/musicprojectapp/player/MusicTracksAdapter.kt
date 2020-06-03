package by.iluxaz.musicprojectapp.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.iluxaz.musicprojectapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class MusicTracksAdapter(val list: ArrayList<MusicTracks>) :
    RecyclerView.Adapter<MusicTracksAdapter.MusicTracksViewHolder>() {

    class MusicTracksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicTracksViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent,false)
        return MusicTracksViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MusicTracksViewHolder, position : Int ){
        val myView = holder.itemView

        myView.trackListNameOfArtist.text = list[position].nameOfArtist
        myView.trackListNameOfTrack.text = list[position].nameOfTrack
        myView.textTimeDuration.text = list[position].timeDuration
        Picasso.get().load(list[position].coverImage).fit().centerCrop().into(myView.trackListImage)


        myView.setOnClickListener {
            Toast.makeText(holder.itemView.context, list[position].nameOfTrack,Toast.LENGTH_SHORT)
                .show()
        }

    }
}