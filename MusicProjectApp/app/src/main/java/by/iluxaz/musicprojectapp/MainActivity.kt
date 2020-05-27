package by.iluxaz.musicprojectapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import by.iluxaz.musicprojectapp.player.AloneActivity
import by.iluxaz.musicprojectapp.player.DoNotThinkActivity
import by.iluxaz.musicprojectapp.player.MomentActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        momentFragmentContainer.setOnClickListener {
            val intent = Intent(this, MomentActivity::class.java)
            startActivity(intent)
        }

        aloneFragmentContainer.setOnClickListener {
            val intent = Intent(this, AloneActivity::class.java)
            startActivity(intent)
        }

        doNotThinkFragmentContainer.setOnClickListener {
            val intent = Intent(this, DoNotThinkActivity::class.java)
            startActivity(intent)
        }
    }
}





