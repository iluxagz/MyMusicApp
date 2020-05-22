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

open class MomentActivity : AppCompatActivity(), View.OnClickListener,
    SeekBar.OnSeekBarChangeListener {

    private val momentMusic = intArrayOf(
        R.raw.luqus_ill_need_you,
        R.raw.menual_amplitude,
        R.raw.pensees_menual_disconnect
    )

    private val momentMusicCover = intArrayOf(
        R.drawable.feel_the_moment,
        R.drawable.feel_the_moment2,
        R.drawable.feel_the_moment3
    )

    val artistOfTrack = arrayListOf("Luqus", "Menual", "Pensees & Menual")
    val nameOfTrack = arrayListOf("I'll Need You", "Amplitude", "Disconnect")

    var musicPosition = 0
    var handler = Handler()

    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player_layout)
        mediaPlayer = MediaPlayer()
        mediaController = MediaController(this)
        playerSeekBar.progress = 0
        playerSeekBar.max = 100
        createMediaPlayer(musicPosition)
        playerSeekBar.setOnSeekBarChangeListener(this)
        playButton.setOnClickListener(this)
        nextButton.setOnClickListener(this)
        previousButton.setOnClickListener(this)

        backToMenuButton.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        listOfTracksButton.setOnClickListener {
            val intent = Intent(this, TrackListActivity::class.java)
            startActivity(intent)

        }
    }

    fun milliSecondsToString(time: Int): String {
        var correctTime: String
        var sec = TimeUnit.MILLISECONDS.toSeconds(time.toLong())
        var min = TimeUnit.SECONDS.toMinutes(sec)
        sec %= 60
        correctTime = "$min : "
        if (sec < 10)
            correctTime += "0"
        return correctTime + "$sec"
    }


    fun createMediaPlayer(position: Int) {
        mediaPlayer = MediaPlayer.create(this, momentMusic[position])
        mediaPlayer.isLooping
    }

    fun playAudio(position: Int) {
        var seekBarProgress = SeekBarProgressThread()
        handler.postDelayed(seekBarProgress, 50)

        playerSeekBar.max = mediaPlayer.duration
        textTimeTotal.text = milliSecondsToString(playerSeekBar.max)
        textCurrentTime.text = milliSecondsToString(mediaPlayer.currentPosition)
        playerSeekBar.progress = mediaPlayer.currentPosition
        titlePhoto.setImageResource(momentMusicCover[position])
        textTitleOfTrack.text = nameOfTrack[position]
        textNameOfArtist.text = artistOfTrack[position]

        mediaPlayer.start()


    }

    fun nextAudio() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
        if (musicPosition < (momentMusic.size - 1)) {
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
        if (musicPosition > 0) {
            musicPosition--
        } else {
            musicPosition = momentMusic.size - 1
        }
        createMediaPlayer(musicPosition)
        playAudio(musicPosition)
    }


    inner class SeekBarProgressThread : Runnable {
        override fun run() {
            var currentTime = mediaPlayer.currentPosition
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