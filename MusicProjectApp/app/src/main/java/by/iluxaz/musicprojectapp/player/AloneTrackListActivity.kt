package by.iluxaz.musicprojectapp.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.iluxaz.musicprojectapp.R
import kotlinx.android.synthetic.main.track_list_layout.*

class AloneTrackListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.track_list_layout)

        val musicTracksCollection = AloneTracksCollection
        musicTracksCollection.instance.musicCollection

        recyclerView.adapter = MusicTracksAdapter(AloneTracksCollection.instance.musicCollection)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

}