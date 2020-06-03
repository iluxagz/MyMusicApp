package by.iluxaz.musicprojectapp.player

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import by.iluxaz.musicprojectapp.MainActivity
import by.iluxaz.musicprojectapp.R
import kotlinx.android.synthetic.main.music_player_layout.*
import java.util.concurrent.TimeUnit

class AloneActivity : AppCompatActivity(), View.OnClickListener,
    SeekBar.OnSeekBarChangeListener {

    private val aloneMusic = intArrayOf(
        R.raw.akira_yamaoka_never_forgive_me_never_forget_me,
        R.raw.cry_of_fear_brandon2,
        R.raw.surrenderdorothy_be_careful_up_there,
        R.raw.orlogin_dont_come_close,
        R.raw.i_feel_like_dirt_bones,
        R.raw.akira_yamaoka_alessas_theme,
        R.raw.orlogin_sirens,
        R.raw.fable_interlude,
        R.raw.bones_disgrace,
        R.raw.akira_yamaoka_fortunate_sleep,
        R.raw.muddasheep_cry_of_fear,
        R.raw.simply_obscurity,
        R.raw.bones_kickingthebucket
    )

    private val aloneMusicCover = intArrayOf(
        R.drawable.feel_yourself_alone8,
        R.drawable.feel_yourself_alone2,
        R.drawable.feel_yourself_alone9,
        R.drawable.feel_yourself_alone,
        R.drawable.feel_yourself_alone13,
        R.drawable.feel_yourself_alone4,
        R.drawable.feel_yourself_alone10,
        R.drawable.feel_yourself_alone5,
        R.drawable.feel_yourself_alone6,
        R.drawable.feel_yourself_alone3,
        R.drawable.feel_yourself_alone11,
        R.drawable.feel_yourself_alone7,
        R.drawable.feel_yourself_alone12
        )

    private val artistOfTrackFromAloneMusic = arrayListOf(
        "Akira Yamaoka",
        "Brandon",
        "SurrenderDorothy",
        "Orlogin",
        "Bones",
        "Akira Yamaoka",
        "Orlogin",
        "Fable",
        "Bones",
        "Akira Yamaoka",
        "Muddasheep",
        "Simply",
        "Bones"
    )

    private val nameOfTrackFromAloneMusic = arrayListOf(
        "Never Forgive Me",
        "Cry Of Fear",
        "Be Careful Up There",
        "Don't Come Close",
        "IFeelLikeDirt",
        "Alessa's Theme",
        "Sirens",
        "Interlude",
        "Disgrace",
        "Fortunate Sleep",
        "Screech",
        "Obscurity",
        "KickingTheBucket"
    )

    private var musicPosition = 0
    var handler = Handler()

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player_layout)
        mediaController = MediaController(this)
        playerSeekBar.progress = 0
        playerSeekBar.max = 100
        createMediaPlayer(musicPosition)

        playerSeekBar.setOnSeekBarChangeListener(this)
        playButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        previousButton.setOnClickListener(this)


        backToMenuButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        listOfTracksButton.setOnClickListener {
            val intent = Intent(this, AloneTrackListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createMediaPlayer(position: Int) {
        mediaPlayer = MediaPlayer.create(this, aloneMusic[position])
    }

    fun milliSecondsToString(time: Int): String {
        var correctTime: String
        var sec = TimeUnit.MILLISECONDS.toSeconds(time.toLong())
        val min = TimeUnit.SECONDS.toMinutes(sec)
        sec %= 60
        correctTime = "$min : "
        if (sec < 10)
            correctTime += "0"
        return correctTime + "$sec"
    }

    fun playAudio(position: Int) {
        val seekBarProgress = SeekBarProgressThread()
        handler.postDelayed(seekBarProgress, 50)
        playerSeekBar.max = mediaPlayer.duration
        playerSeekBar.progress = mediaPlayer.currentPosition

        textTimeTotal.text = milliSecondsToString(playerSeekBar.max)
        textCurrentTime.text = milliSecondsToString(playerSeekBar.progress)
        textTitleOfTrack.text = nameOfTrackFromAloneMusic[position]
        textNameOfArtist.text = artistOfTrackFromAloneMusic[position]
        titlePhoto.setImageResource(aloneMusicCover[position])


        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            nextAudio()
        }

    }

    fun nextAudio() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
        mediaPlayer.release()
        if (musicPosition < (aloneMusic.size - 1)) {
            musicPosition++
        } else {
            musicPosition = 0
        }
        createMediaPlayer(musicPosition)
        playAudio(musicPosition)
    }

    fun previousAudio() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
        mediaPlayer.release()
        if (musicPosition > 0) {
            musicPosition--
        } else {
            musicPosition = aloneMusic.size - 1
        }
        createMediaPlayer(musicPosition)
        playAudio(musicPosition)
    }

    inner class SeekBarProgressThread : Runnable {
        override fun run() {
            val currentTime = mediaPlayer.currentPosition
            textCurrentTime.text = milliSecondsToString(currentTime)
            playerSeekBar.progress = currentTime

            if (currentTime != mediaPlayer.duration)
                handler.postDelayed(this, 50)

        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.playButton -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    playButton.setImageResource(R.drawable.ic_play)
                } else {
                    playButton.setImageResource(R.drawable.ic_pause)
                    playAudio(musicPosition)
                    mediaPlayer.start()
                }
            }
            R.id.nextButton -> {
                nextAudio()
            }
            R.id.previousButton -> {
                previousAudio()
            }
        }
    }


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.progress?.let {
            mediaPlayer.seekTo(it)
        }
    }
}

