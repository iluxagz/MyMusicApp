package by.iluxaz.musicprojectapp.player

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.iluxaz.musicprojectapp.MainActivity
import by.iluxaz.musicprojectapp.R
import kotlinx.android.synthetic.main.music_player_layout.*

class DoNotThinkActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player_layout)

        backToMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}